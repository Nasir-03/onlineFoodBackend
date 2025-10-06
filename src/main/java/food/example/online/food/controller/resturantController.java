package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.ResturantDTO;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.service.ResturantService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api/resturants")
public class resturantController {

	private ResturantService resturantService;
	private UserService userService;
	
	@Autowired
	public resturantController(ResturantService resturantService, UserService userService) {
		this.resturantService = resturantService;
		this.userService = userService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Resturant>> searchResturant(
			@RequestHeader("Authorization") String jwt,
			@RequestParam String keyword){
		User user = userService.findUserByJwt(jwt);
		List<Resturant> resturant = resturantService.searchResturant(keyword);
		return new ResponseEntity<List<Resturant>>(resturant,HttpStatus.OK);
	}
	
//	@GetMapping("/getAll")
//	public ResponseEntity<List<Resturant>> getAllResturant(
//			@RequestHeader("Authorization") String jwt){
//		User user = userService.findUserByJwt(jwt);
//		List<Resturant> resturant = resturantService.getAllResturant();
//		return new ResponseEntity<List<Resturant>>(resturant,HttpStatus.OK);
//	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Resturant>> getAllResturant(){
	    List<Resturant> resturant = resturantService.getAllResturant();
	    return new ResponseEntity<>(resturant, HttpStatus.OK);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Resturant> findResturantById(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception{
		User user = userService.findUserByJwt(jwt);
		Resturant resturant = resturantService.findResturantById(id);
		return new ResponseEntity<Resturant>(resturant,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/add-favouraite")
	public ResponseEntity<Resturant> addToFavouraites(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception{
		User user = userService.findUserByJwt(jwt);
		Resturant resturant = resturantService.addToFavourites(id,user);
		return new ResponseEntity<Resturant>(resturant,HttpStatus.OK);
	}
	
	@GetMapping("/favourites")
	public ResponseEntity<List<Resturant>> getUserFavourites(
	        @RequestHeader("Authorization") String jwt) throws Exception {
	    User user = userService.findUserByJwt(jwt);
	    List<Resturant> favourites = resturantService.getFavouritesByUserId(user.getId());
	    return ResponseEntity.ok(favourites);
	}

}
