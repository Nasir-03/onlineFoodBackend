package food.example.online.food.mapper;

import food.example.online.food.dto.UserDTO;
import food.example.online.food.entity.User;


public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getFullName(),
                dto.getEmail(),
                null,  
                dto.getRole(),
                dto.getOrders(),
                dto.getFavorites(),
                dto.getAddresses()
        );
    }

    public static UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getOrders(),
                user.getFavorites(),
                user.getAddresses()
        );
    }
}
