package food.example.online.food.dto;

import java.util.ArrayList;
import java.util.List;

import food.example.online.food.entity.Address;
import food.example.online.food.entity.Order;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.UserRole;

public class UserDTO {

	private Long id;

	private String fullName;
	private String email;

	private UserRole role;

	private List<Order> orders = new ArrayList<>();

	private List<Resturant> favorites = new ArrayList<>();

	private List<Address> addresses = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Resturant> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Resturant> favorites) {
		this.favorites = favorites;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public UserDTO(Long id, String fullName, String email, UserRole role, List<Order> orders,
			List<Resturant> favorites, List<Address> addresses) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.role = role;
		this.orders = orders;
		this.favorites = favorites;
		this.addresses = addresses;
	}

	public UserDTO() {
		super();
	}	
}
