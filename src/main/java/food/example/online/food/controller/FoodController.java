package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.entity.Food;
import food.example.online.food.entity.User;
import food.example.online.food.service.FoodService;
import food.example.online.food.service.ResturantService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	private FoodService foodService;
	private UserService userService;
	private ResturantService resturantService;
	
	@Autowired
	public FoodController(FoodService foodService, UserService userService, ResturantService resturantService) {
		this.foodService = foodService;
		this.userService = userService;
		this.resturantService = resturantService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
	                                       @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		List<Food> foods = foodService.searcFoods(keyword);
		
		return new ResponseEntity<List<Food>>(foods,HttpStatus.OK);
	}
	
	@GetMapping("/resturant/{id}")
	public ResponseEntity<List<Food>> getResturantFood(
           @RequestParam boolean vegeterian,
           @RequestParam boolean seasonal,
           @RequestParam boolean nonVeg,
           @RequestParam(required = false) String food_category,
           @PathVariable Long id,
           @RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		List<Food> foods = foodService.getResturantsFoods(id, vegeterian, nonVeg, seasonal, food_category);
		
		return new ResponseEntity<List<Food>>(foods,HttpStatus.OK);
	}
}
