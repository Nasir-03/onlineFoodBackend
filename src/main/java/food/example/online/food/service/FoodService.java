package food.example.online.food.service;

import java.util.List;

import org.springframework.stereotype.Service;

import food.example.online.food.dto.FoodRequest;
import food.example.online.food.entity.Category;
import food.example.online.food.entity.Food;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;

@Service
public interface FoodService{

//	public Food createFood(FoodRequest req, Category category,
//			               Resturant resturant);
	
	public Food createFood(FoodRequest req) throws Exception;
	
	public void deleteFood(Long foodId,User user)throws Exception;
	
	public List<Food> getResturantsFoods(Long resturantId,
			boolean isVegeterian,boolean isNonVeg,
			boolean isSessional,String foodCategory)throws Exception;
	
	public List<Food> searcFoods(String keyword);
	
	public Food findFoodById(Long foodId)throws Exception;
	
	public Food updateAvailabilityStatus(Long foodId)throws Exception;
	
	
}
