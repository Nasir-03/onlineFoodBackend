package food.example.online.food.dto;

import java.util.List;

import food.example.online.food.entity.Category;
import food.example.online.food.entity.IngredientsItem;

public class FoodRequest {
	    
	    private String name;
	    private String description;
	    private Long price;
	    private boolean vegeterian;
	    private boolean seasonal;   // ✅ not "sessional"
	    private List<IngredientsItem> ingredients;
	    private List<String> images;
	    private Category category;
	    private Long resturantId;
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Long getPrice() {
			return price;
		}
		public void setPrice(Long price) {
			this.price = price;
		}
		public boolean isVegeterian() {
			return vegeterian;
		}
		public void setVegeterian(boolean vegeterian) {
			this.vegeterian = vegeterian;
		}
		public boolean isSeasonal() {
			return seasonal;
		}
		public void setSeasonal(boolean seasonal) {
			this.seasonal = seasonal;
		}
		public List<IngredientsItem> getIngredients() {
			return ingredients;
		}
		public void setIngredients(List<IngredientsItem> ingredients) {
			this.ingredients = ingredients;
		}
		public List<String> getImages() {
			return images;
		}
		public void setImages(List<String> images) {
			this.images = images;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		public Long getResturantId() {
			return resturantId;
		}
		public void setResturantId(Long resturantId) {
			this.resturantId = resturantId;
		}
	    
	    
			    
}





