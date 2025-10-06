package food.example.online.food.service;

import java.util.List;

import food.example.online.food.entity.Category;

public interface CategoryService {

	public Category createCategory(String name,Long userId,Long resturantId)throws Exception;
	
	public List<Category> findCategoryByResturantId(Long resturantId)throws Exception;
	
	public Category findCategoryById(Long id);
	
	public void deleteCategory(Long categoryId,Long ResturantId);
}
