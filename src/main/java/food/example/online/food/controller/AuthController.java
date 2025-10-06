package food.example.online.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.config.LoginReq;
import food.example.online.food.config.RegisterRequest;
import food.example.online.food.config.loginResp;
import food.example.online.food.entity.User;
import food.example.online.food.exception.UsernameAlreadyFoundException;
import food.example.online.food.repository.UserRepository;
import food.example.online.food.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserRepository userRepository;
	
	 @PostMapping("/register")
	    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) throws UsernameAlreadyFoundException {
	        User user = authService.register(request);
	        return new ResponseEntity<>(user, HttpStatus.CREATED);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<loginResp> login(@RequestBody LoginReq request) {
	        loginResp response = authService.login(request);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
}
