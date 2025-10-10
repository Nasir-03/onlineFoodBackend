package food.example.online.food.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import food.example.online.food.config.JwtUtill;
import food.example.online.food.config.LoginReq;
import food.example.online.food.config.MyUserDetailsService;
import food.example.online.food.config.RegisterRequest;
import food.example.online.food.config.loginResp;
import food.example.online.food.entity.Cart;
import food.example.online.food.entity.User;
import food.example.online.food.exception.UsernameAlreadyFoundException;
import food.example.online.food.repository.CartRepository;
import food.example.online.food.repository.UserRepository;

@Service
public class AuthService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtUtill jwtUtill;
	private MyUserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private CartRepository cartRepository;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtill jwtUtill,
			MyUserDetailsService userDetailsService,AuthenticationManager authenticationManager,
			CartRepository cartRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtill = jwtUtill;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.cartRepository = cartRepository;
	}
	

	public User register(RegisterRequest request) throws UsernameAlreadyFoundException {
		if (userRepository.findByEmail(request.getEmail()) != null) {
			throw new UsernameAlreadyFoundException("Email is already registered: " + request.getEmail());
		}
		User user = new User();
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		
		User savedUser = userRepository.save(user);
		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		return savedUser;
	}
	
	

	 public loginResp login(LoginReq request) {
		 
		 User user = userRepository.findByEmail(request.getEmail());
		    if (user == null) {
		        throw new UsernameNotFoundException("Email is not register");
		    }

		    // 2. Check password
		    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
		        throw new BadCredentialsException("Password is not metched");
		    }

		    // 3. Generate JWT
		    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		    final String jwt = jwtUtill.generateToken(userDetails);

		    return new loginResp(jwt);
	    }
}
