package food.example.online.food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import food.example.online.food.entity.Category;
import food.example.online.food.entity.IngredientCategory;
import food.example.online.food.entity.IngredientsItem;
import food.example.online.food.entity.Resturant;
import food.example.online.food.exception.UsernameAlreadyFoundException;
import food.example.online.food.repository.IngredientsCategoryRepository;
import food.example.online.food.repository.IngredientsItemsRepository;

@Service
public class IngredientsServiceImpl implements IngredientsService{

	private IngredientsCategoryRepository ingredientsCategoryRepository;
	private IngredientsItemsRepository ingredientsItemsRepository;
	private ResturantService resturantService;
	
	public IngredientsServiceImpl(IngredientsCategoryRepository ingredientsCategoryRepository,
			IngredientsItemsRepository ingredientsItemsRepository,ResturantService resturantService) {
		this.ingredientsCategoryRepository = ingredientsCategoryRepository;
		this.ingredientsItemsRepository = ingredientsItemsRepository;
	    this.resturantService = resturantService;
	}

	@Override
	public IngredientCategory createIngredientCategory(String name, Long resturantId) throws Exception {
		Resturant resturant = resturantService.findResturantById(resturantId);
		IngredientCategory category = new IngredientCategory();
		category.setName(name);
		category.setResturant(resturant);
		return ingredientsCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		return ingredientsCategoryRepository.findById(id).orElseThrow(
				()-> new UsernameAlreadyFoundException("ingredient not found"));
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByResturantId(Long id) throws Exception {
		resturantService.findResturantById(id);
		
		return ingredientsCategoryRepository.findByResturantId(id);
	}

	@Override
	public IngredientsItem createIngredientItems(Long resturantId, String ingredientName, Long categoryId)
			throws Exception {
		Resturant resturant = resturantService.findResturantById(resturantId);
		IngredientCategory category = findIngredientCategoryById(categoryId);
		
		IngredientsItem ingredientsItem = new IngredientsItem();
		
		ingredientsItem.setName(ingredientName);
		ingredientsItem.setResturant(resturant);
		ingredientsItem.setCategory(category);
		
		IngredientsItem items = ingredientsItemsRepository.save(ingredientsItem);
		
		category.getIngredients().add(items);
		
		return items;
	}

	@Override
	public List<IngredientsItem> findResturantIngredients(Long resturantId) {
		return ingredientsItemsRepository.findByResturantId(resturantId);
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> optionalIngredientItems = ingredientsItemsRepository.findById(id);
		
		if (optionalIngredientItems.isEmpty()) {
			throw new Exception("ingredient not found");
		}
		IngredientsItem ingredientsItem = optionalIngredientItems.get();
		ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
		return ingredientsItemsRepository.save(ingredientsItem);
	}

}
