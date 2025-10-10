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
import food.example.online.food.entity.OtpVerification;
import food.example.online.food.entity.User;
import food.example.online.food.exception.UsernameAlreadyFoundException;
import food.example.online.food.repository.CartRepository;
import food.example.online.food.repository.OtpRepository;
import food.example.online.food.repository.UserRepository;

@Service
public class AuthService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtUtill jwtUtill;
	private MyUserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private CartRepository cartRepository;
	private EmailService emailService;
	private OtpRepository otpRepository;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtill jwtUtill,
			MyUserDetailsService userDetailsService,AuthenticationManager authenticationManager,
			CartRepository cartRepository,EmailService emailService,OtpRepository otpRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtill = jwtUtill;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.cartRepository = cartRepository;
		this.emailService = emailService;
		this.otpRepository = otpRepository;
	}
	

//	public User register(RegisterRequest request) throws UsernameAlreadyFoundException {
//		if (userRepository.findByEmail(request.getEmail()) != null) {
//			throw new UsernameAlreadyFoundException("Email is already registered: " + request.getEmail());
//		}
//		User user = new User();
//		user.setFullName(request.getFullName());
//		user.setEmail(request.getEmail());
//		user.setPassword(passwordEncoder.encode(request.getPassword()));
//		user.setRole(request.getRole());
//		
//		User savedUser = userRepository.save(user);
//		Cart cart = new Cart();
//		cart.setCustomer(savedUser);
//		cartRepository.save(cart);
//		return savedUser;
//	}
	
	
	 // Step 1: Send OTP (instead of immediate register)
    public String sendOtp(RegisterRequest request) throws UsernameAlreadyFoundException {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new UsernameAlreadyFoundException("Email is already registered: " + request.getEmail());
        }

        String otp = String.format("%04d", new java.util.Random().nextInt(10000));
        OtpVerification otpRecord = new OtpVerification();
        otpRecord.setEmail(request.getEmail());
        otpRecord.setOtp(otp);
        otpRecord.setExpiryTime(java.time.LocalDateTime.now().plusMinutes(5));
        otpRecord.setVerified(false);
        otpRepository.save(otpRecord);

        emailService.sendOtp(request.getEmail(), otp);
        return "OTP sent to " + request.getEmail();
    }

    // Step 2: Verify OTP and then save user
    public User verifyOtpAndRegister(String email, String otp, RegisterRequest request) {
        OtpVerification otpRecord = otpRepository.findTopByEmailOrderByExpiryTimeDesc(email)
                .orElseThrow(() -> new RuntimeException("No OTP found for this email"));

        if (otpRecord.getExpiryTime().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (!otpRecord.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        otpRecord.setVerified(true);
        otpRepository.save(otpRecord);

        // Now save actual user
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
