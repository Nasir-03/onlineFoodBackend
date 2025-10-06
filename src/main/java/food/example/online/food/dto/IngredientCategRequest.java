package food.example.online.food.dto;

public class IngredientCategRequest {

	private String name;
	private Long resturantId;
	
	public IngredientCategRequest(String name, Long resturantId) {
		super();
		this.name = name;
		this.resturantId = resturantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getResturantId() {
		return resturantId;
	}
	public void setResturantId(Long resturantId) {
		this.resturantId = resturantId;
	}
	
	
}
