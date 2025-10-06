package food.example.online.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.UserDTO;
import food.example.online.food.entity.User;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String token) {
	    if (token.startsWith("Bearer ")) {
	        token = token.substring(7); // remove "Bearer "
	    }
	    User user = userService.findUserByJwt(token);
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
