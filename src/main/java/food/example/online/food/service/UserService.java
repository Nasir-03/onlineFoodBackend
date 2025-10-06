package food.example.online.food.service;

import org.springframework.stereotype.Service;

import food.example.online.food.dto.UserDTO;
import food.example.online.food.entity.User;

public interface UserService {

	public UserDTO getUserByEmail(String email);
	
	public User findUserByJwt(String token);
}
