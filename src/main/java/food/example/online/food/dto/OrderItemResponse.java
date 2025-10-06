// OrderItemResponse.java
package food.example.online.food.dto;

import java.util.List;

public class OrderItemResponse {
    private Long id;
    private String foodName;
    private int quantity;
    private double totalPrice;
    private List<String> ingredients;
    private String image;
    
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public OrderItemResponse(Long id, String foodName, int quantity, double totalPrice, List<String> ingredients,String image) {
		this.id = id;
		this.foodName = foodName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.ingredients = ingredients;
		this.image = image;
	}
    
    public OrderItemResponse() {
    	super();
    }
}
