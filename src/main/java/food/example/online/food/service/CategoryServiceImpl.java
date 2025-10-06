package food.example.online.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import food.example.online.food.entity.Category;
import food.example.online.food.entity.Resturant;
import food.example.online.food.repository.CategoryRepository;
import food.example.online.food.repository.ResturantRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ResturantService resturantService;
	private ResturantRepository resturantRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ResturantService resturantService,ResturantRepository resturantRepository) {
		this.categoryRepository = categoryRepository;
		this.resturantService = resturantService;
		this.resturantRepository = resturantRepository;
	}

	@Override
	public Category createCategory(String name, Long userId, Long resturantId) throws Exception {
		Resturant resturant = resturantService.findResturantById(resturantId);

		// ✅ check if user is the owner
		if (!resturant.getOwner().getId().equals(userId)) {
			throw new Exception("You are not the owner of this restaurant.");
		}

		// ✅ fetch existing categories
		List<Category> categories = findCategoryByResturantId(resturantId);

		// ✅ check if category name already exists (case-insensitive)
		boolean exists = categories.stream().anyMatch(c -> c.getName().equalsIgnoreCase(name));

		if (exists) {
			throw new Exception("Category already available");
		}

		// ✅ create and save new category
		Category newCategory = new Category();
		newCategory.setName(name);
		newCategory.setResturant(resturant);

		return categoryRepository.save(newCategory);
	}

//	@Override
//	public List<Category> findCategoryByResturantId(Long resturantId) {
//	    // ✅ validate existence
//	    try {
//			resturantService.findResturantByUserId(resturantId);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	    return categoryRepository.findByResturantId(resturantId);
//	}

	@Override
	public List<Category> findCategoryByResturantId(Long resturantId) throws Exception {
		// ✅ validate restaurant existence
		resturantService.findResturantById(resturantId);

		// ✅ fetch categories
		return categoryRepository.findByResturantId(resturantId);
	}

	@Override
	public Category findCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("category not found"));
		return category;
	}

	@Override
	@Transactional
	public void deleteCategory(Long categoryId, Long resturantId) {
	    Resturant resturant = resturantRepository.findById(resturantId)
	            .orElseThrow(() -> new UsernameNotFoundException("Restaurant not found"));

	    Category category = categoryRepository.findById(categoryId)
	            .orElseThrow(() -> new UsernameNotFoundException("Category not found"));

	    // ensure the category belongs to this restaurant
	    if (!category.getResturant().getId().equals(resturantId)) {
	        throw new IllegalArgumentException("This category does not belong to the given restaurant");
	    }

	    // remove from restaurant’s categories → orphanRemoval will auto-delete it
	    resturant.getCategories().remove(category);

	    resturantRepository.save(resturant); // JPA handles deletion automatically
	}

	

}
