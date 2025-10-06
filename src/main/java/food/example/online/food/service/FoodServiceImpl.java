package food.example.online.food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import food.example.online.food.dto.FoodRequest;
import food.example.online.food.entity.Category;
import food.example.online.food.entity.Food;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.repository.CategoryRepository;
import food.example.online.food.repository.FoodRepository;
import food.example.online.food.repository.IngredientsItemsRepository;
import food.example.online.food.repository.ResturantRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FoodServiceImpl implements FoodService{

	private FoodRepository foodRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private IngredientsItemsRepository ingredientsItemsRepository;
	@Autowired
	private ResturantRepository resturantRepository;


	@Autowired
	public FoodServiceImpl(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}


	@Override
	 public Food createFood(FoodRequest req) throws Exception {
	        // 🔹 Fetch Restaurant (must exist, otherwise error)
	        Resturant resturant = resturantRepository.findById(req.getResturantId())
	                .orElseThrow(() -> new Exception("Restaurant not found with id: " + req.getResturantId()));

	        // 🔹 Handle Category (create if not exists)
	        Category category;
	        if (req.getCategory() != null && req.getCategory().getId() != null) {
	            category = categoryRepository.findById(req.getCategory().getId())
	                    .orElseThrow(() -> new Exception("Category not found with id: " + req.getCategory().getId()));
	        } else if (req.getCategory() != null && req.getCategory().getName() != null) {
	            // check by name first
	            category = categoryRepository.findByNameAndResturant(req.getCategory().getName(), resturant)
	                    .orElseGet(() -> {
	                        Category newCategory = new Category();
	                        newCategory.setName(req.getCategory().getName());
	                        newCategory.setResturant(resturant);
	                        return categoryRepository.save(newCategory);
	                    });
	        } else {
	            throw new Exception("Category is required!");
	        }

	        // 🔹 Build Food
	        Food food = new Food();
	        food.setFoodCategory(category);
	        food.setResturant(resturant);
	        food.setDescription(req.getDescription());
	        food.setImages(req.getImages());
	        food.setName(req.getName());
	        food.setPrice(req.getPrice());
	        food.setIngredients(req.getIngredients());
	        food.setSeasonal(req.isSeasonal());
	        food.setVegeterian(req.isVegeterian());
	        food.setAvailable(true); // default available

	        // 🔹 Save Food
	        Food savedFood = foodRepository.save(food);
	        resturant.getFoods().add(savedFood);

	        return savedFood;
	    }


//	@Override
//	public List<Food> getResturantsFoods(Long resturantId,
//			boolean isVegeterian, boolean isNonVeg, 
//			boolean isSessional,
//			String foodCategory) {
//		
//		 List<Food> foods = foodRepository.findByResturantId(resturantId);
//		 if (foods == null) {
//			 return (List<Food>) new EntityNotFoundException("food not found in this resturant");
//		 }
//		 
//		 if (isVegeterian) {
//			 foods = filterByVegeterian(foods,isVegeterian);
//		 }
//		 
//		 if (isNonVeg) {
//			 foods = filterByNonVeg(foods,isNonVeg);
//		 }
//		 
//		 if (isSessional) {
//			 foods = filterBySeasonal(foods,isSessional);
//		 }
//		 
//		 if (foodCategory != null && !foodCategory.equals("")) {
//			 foods = filterByCategory(foods,foodCategory);
//		 }
//		return foods;
//	}
	
	@Override
	public List<Food> getResturantsFoods(Long resturantId,
	        boolean isVegeterian, boolean isNonVeg, 
	        boolean isSessional,
	        String foodCategory) {
	    
	    List<Food> foods = foodRepository.findByResturantId(resturantId);
	    if (foods == null || foods.isEmpty()) {
	    	 foods = new ArrayList<>();
	    }

	    // Only apply filters if flag is true
	    if (isVegeterian) {
	        foods = filterByVegeterian(foods, true);
	    }
	    
	    if (isNonVeg) {
	        foods = filterByNonVeg(foods, true);
	    }
	    
	    if (isSessional) {
	        foods = filterBySeasonal(foods, true);
	    }
	    
	    if (foodCategory != null && !foodCategory.isEmpty()) {
	        foods = filterByCategory(foods, foodCategory);
	    }

	    return foods;
	}


	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food -> {
			if (food.getFoodCategory() != null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSessional) {
		return foods.stream().filter(food -> food.isSeasonal() == isSessional).collect(Collectors.toList());
	}
	
	private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
	    return foods.stream().filter(food -> !food.isVegeterian() == isNonVeg).collect(Collectors.toList());
	}


	private List<Food> filterByVegeterian(List<Food> foods, boolean isVegeterian) {
		return foods.stream().filter(food -> food.isVegeterian() == isVegeterian).collect(Collectors.toList());
	}

	@Override
	public List<Food> searcFoods(String keyword) {
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) {
	    return foodRepository.findById(foodId)
	            .orElseThrow(() -> new EntityNotFoundException("Food item not found with id: " + foodId));
	}


	@Override
	public Food updateAvailabilityStatus(Long foodId) {
		Food food = foodRepository.findById(foodId)
	            .orElseThrow(() -> new EntityNotFoundException("Food item not found with id: " + foodId));
	
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

	@Override
	public void deleteFood(Long foodId, User user) throws Exception {
	    Food food = foodRepository.findById(foodId)
	            .orElseThrow(() -> new EntityNotFoundException("Food not found with id: " + foodId));

	    // Check ownership
	    if (!food.getResturant().getOwner().getId().equals(user.getId())) {
	        throw new Exception("You are not allowed to delete this food"); // or custom ForbiddenException
	    }

	    // Safe delete
	    food.setResturant(null);
	    foodRepository.save(food);
	}

}
