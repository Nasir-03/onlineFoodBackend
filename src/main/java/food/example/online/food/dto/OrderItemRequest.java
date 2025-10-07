package food.example.online.food.dto;

import java.util.List;

public class OrderItemRequest {

	 private Long foodId;
	    private String foodName;
	    private Integer quantity;
	    private Double totalPrice;
	    private List<String> ingredients;
		public Long getFoodId() {
			return foodId;
		}
		public void setFoodId(Long foodId) {
			this.foodId = foodId;
		}
		public String getFoodName() {
			return foodName;
		}
		public void setFoodName(String foodName) {
			this.foodName = foodName;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public Double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}
		public List<String> getIngredients() {
			return ingredients;
		}
		public void setIngredients(List<String> ingredients) {
			this.ingredients = ingredients;
		}
	    
	    
}
