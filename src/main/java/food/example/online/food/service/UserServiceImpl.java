package food.example.online.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import food.example.online.food.config.JwtUtill;
import food.example.online.food.dto.UserDTO;
import food.example.online.food.entity.User;
import food.example.online.food.mapper.UserMapper;
import food.example.online.food.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private JwtUtill jwtUtill;
	
	@Autowired
      public UserServiceImpl(UserRepository userRepository,JwtUtill jwtUtill) {
		  this.userRepository = userRepository;
		  this.jwtUtill = jwtUtill;
	 }

	@Override
	public UserDTO getUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return UserMapper.toDto(user);
	}
	
	public User findUserByJwt(String token) {
	    // Strip "Bearer " if present
	    if (token != null && token.startsWith("Bearer ")) {
	        token = token.substring(7);
	    }
	    // Remove accidental whitespace/newlines
	    token = token.trim();

	    String email = jwtUtill.findMailByJwt(token);
	    return userRepository.findByEmail(email);
	}



}
