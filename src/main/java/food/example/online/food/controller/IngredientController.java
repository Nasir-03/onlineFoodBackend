package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.IngredientCategRequest;
import food.example.online.food.dto.IngredientRequest;
import food.example.online.food.entity.IngredientCategory;
import food.example.online.food.entity.IngredientsItem;
import food.example.online.food.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

	private IngredientsService ingredientsService;

	@Autowired
	public IngredientController(IngredientsService ingredientsService) {
		this.ingredientsService = ingredientsService;
	}
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredientCategory(
			@RequestBody IngredientCategRequest req) throws Exception{
		
		IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(req.getName(), req.getResturantId());
		return new ResponseEntity<IngredientCategory>(ingredientCategory,HttpStatus.CREATED);
	}
	
	@PostMapping("/items")
	public ResponseEntity<IngredientsItem> createIngredientItem(
			@RequestBody IngredientRequest req) throws Exception{
		
		IngredientsItem item = ingredientsService.createIngredientItems(req.getResturantId(), req.getName(), req.getCategoryId());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-stock/{id}")
	public ResponseEntity<IngredientsItem> updateStock(
			@PathVariable Long id) throws Exception{
		
		IngredientsItem item = ingredientsService.updateStock(id);
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@GetMapping("/resturant/{id}")
	public ResponseEntity<List<IngredientsItem>> getResturantIngredient(
			@PathVariable Long id) throws Exception{
		
		List<IngredientsItem> item = ingredientsService.findResturantIngredients(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@GetMapping("/resturant/{id}/category")
	public ResponseEntity<List<IngredientCategory>> getResturantIngredientCategory(
			@PathVariable Long id) throws Exception{
		
		List<IngredientCategory> item = ingredientsService.findIngredientCategoryByResturantId(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
}
