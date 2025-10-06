package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.entity.Category;
import food.example.online.food.entity.User;
import food.example.online.food.service.CategoryService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	private CategoryService categoryService;
	private UserService userService;

	@Autowired
	public CategoryController(CategoryService categoryService,UserService userService) {
		this.categoryService = categoryService;
		this.userService = userService;
	}
	
	@PostMapping("/admin/category/{resturantId}")
	public ResponseEntity<Category> createCategory(
	        @RequestBody Category category,
	        @PathVariable Long resturantId,
	        @RequestHeader("Authorization") String jwt) throws Exception {

	    User user = userService.findUserByJwt(jwt);
	    Category category2 = categoryService.createCategory(category.getName(), user.getId(), resturantId);

	    return new ResponseEntity<>(category2, HttpStatus.CREATED);
	}

	@GetMapping("/category/resturant/{resturantId}")
	public ResponseEntity<List<Category>> getResturantCategory(@RequestHeader("Authorization") String jwt,
			@PathVariable Long resturantId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		List<Category> categories = categoryService.findCategoryByResturantId(resturantId);
		
		return new ResponseEntity<>(categories,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/category/{categoryId}/{restaurantId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId,
	                                           @PathVariable Long restaurantId) {
	    categoryService.deleteCategory(categoryId, restaurantId);
	    return ResponseEntity.ok().build(); // 200 OK with no body
	}

}
