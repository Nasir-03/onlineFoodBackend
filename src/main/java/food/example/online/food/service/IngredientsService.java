package food.example.online.food.service;

import java.util.List;

import food.example.online.food.entity.IngredientCategory;
import food.example.online.food.entity.IngredientsItem;

public interface IngredientsService {

	public IngredientCategory createIngredientCategory(String name,Long resturantId)throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id)throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByResturantId(Long id)throws Exception;
	
	public IngredientsItem createIngredientItems(Long resturantId,String ingredientName,Long categoryId)throws Exception;
	
	public List<IngredientsItem> findResturantIngredients(Long resturantId);
	
	public IngredientsItem updateStock(Long id)throws Exception;
}
