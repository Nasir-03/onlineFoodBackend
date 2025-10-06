package food.example.online.food.dto;

import java.util.List;

public class AddCartRequest {

	private Long foodId;
	private int quantity;
	private Long resturantId;
	
	private List<String> ingredients;
	
	
	public AddCartRequest(Long foodId, int quantity, Long resturantId, List<String> ingredients) {
		this.foodId = foodId;
		this.quantity = quantity;
		this.resturantId = resturantId;
		this.ingredients = ingredients;
	}
	public Long getResturantId() {
		return resturantId;
	}
	public void setResturantId(Long resturantId) {
		this.resturantId = resturantId;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
