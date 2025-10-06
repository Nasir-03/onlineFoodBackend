package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.FoodRequest;
import food.example.online.food.entity.Category;
import food.example.online.food.entity.Food;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.repository.CategoryRepository;
import food.example.online.food.repository.UserRepository;
import food.example.online.food.service.FoodService;
import food.example.online.food.service.ResturantService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	private FoodService foodService;
	private UserService userService;
	private ResturantService resturantService;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public AdminFoodController(FoodService foodService, UserService userService, ResturantService resturantService,CategoryRepository categoryRepository) {
		this.foodService = foodService;
		this.userService = userService;
		this.resturantService = resturantService;
        this.categoryRepository = categoryRepository;
	}


//	@PostMapping("/create")
//	public ResponseEntity<Food> createFood(@RequestBody FoodRequest req,
//	                                       @RequestHeader("Authorization") String jwt) throws Exception {
//	    User user = userService.findUserByJwt(jwt);
//	    Resturant resturant = resturantService.findResturantById(req.getResturantId());
//
//	  Food food = foodService.createFood(req, req.getCategory(), resturant);
//	    return new ResponseEntity<>(food, HttpStatus.CREATED); // ✅ better status than OK
//	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createFood(@RequestBody FoodRequest req) {
	    try {
	        Food savedFood = foodService.createFood(req);
	        return ResponseEntity.ok(savedFood);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	
	
	@DeleteMapping("/delete/{id}")
	public void deleteFood(@PathVariable Long id,
	                                       @RequestHeader("Authorization") String jwt) throws Exception {
	    User user = userService.findUserByJwt(jwt);
	     foodService.deleteFood(id,user);
	}
	
	@PutMapping("/updateAvail/{id}")
	public ResponseEntity<Food> updateAvailabilityStatus(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		Food food = foodService.updateAvailabilityStatus(id);
	
	return new ResponseEntity<Food>(food,HttpStatus.OK);
	}

}
