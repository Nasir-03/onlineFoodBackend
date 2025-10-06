package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.ResturantReq;
import food.example.online.food.dto.ResturantResponseDTO;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.service.ResturantService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api/admin/resturant")
public class AdminResturantController {

	private ResturantService resturantService;
	private UserService userService;
	
	@Autowired
	public AdminResturantController(ResturantService resturantService, UserService userService) {
		super();
		this.resturantService = resturantService;
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Resturant> createResturant(@RequestBody ResturantReq req, @RequestHeader("Authorization") String jwt){
		User user = userService.findUserByJwt(jwt);
		Resturant resturant = resturantService.createResturant(req, user);
		return new ResponseEntity<Resturant>(resturant,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Resturant> updateResturant(@RequestBody ResturantReq req,@RequestHeader("Authorization") String jwt,@PathVariable Long id){
		User user = userService.findUserByJwt(jwt);
		Resturant resturant = resturantService.updateResturant(id,req);
		return new ResponseEntity<Resturant>(resturant,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteResturant(@PathVariable Long id ,@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		resturantService.deleteResturant(id);
	}
	
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<Resturant> updateResturantStatus(
	        @RequestHeader("Authorization") String token,
	        @PathVariable Long id) throws Exception {

	   User user = userService.findUserByJwt(token);

	    // ✅ update status
	    Resturant resturant = resturantService.updateResturantStatus(id);
	    return new ResponseEntity<>(resturant, HttpStatus.OK);
	}
	
	@GetMapping("/findUserById")
	public ResponseEntity<List<ResturantResponseDTO>> findResturantByUserId(
			@RequestHeader("Authorization") String token) throws Exception {

	   User user = userService.findUserByJwt(token);

	    // ✅ update status
	    List<ResturantResponseDTO> resturant = resturantService.findResturantByUserId(user.getId());
	    return new ResponseEntity<>(resturant, HttpStatus.OK);
	}

}
