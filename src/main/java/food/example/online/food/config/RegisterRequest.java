package food.example.online.food.config;

import food.example.online.food.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {

	@NotBlank(message =  "Name is required")
	private String fullName;
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "password is required filled")
    @Pattern(
   		  regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
   		  message = "Password must be 8–15 characters long and include upper case, lower case, digit, and special character.")
	
	private String password;
	private UserRole role;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public RegisterRequest(String fullName, String email, String password, UserRole role) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public RegisterRequest() {
		super();
	}
	
	
}
