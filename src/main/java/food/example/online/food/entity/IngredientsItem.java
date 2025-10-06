package food.example.online.food.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class IngredientsItem {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String name;
//	
	@ManyToOne
	private IngredientCategory category;
//	
//	@JsonIgnore
//	@ManyToOne
//	private Resturant resturant;
//	
	private boolean inStoke = true;
//	
//	@ManyToMany(mappedBy = "ingredients")
//	private List<Food> foods = new ArrayList<>();
//	
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ManyToOne
	    @JoinColumn(name = "resturant_id")
	    private Resturant resturant;

	    @ManyToMany(mappedBy = "ingredients")
	    @JsonIgnore
	    private List<Food> foods = new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Resturant getResturant() {
			return resturant;
		}

		public void setResturant(Resturant resturant) {
			this.resturant = resturant;
		}

		public List<Food> getFoods() {
			return foods;
		}

		public void setFoods(List<Food> foods) {
			this.foods = foods;
		}
		
		public IngredientCategory getCategory() {
			return category;
		}

		public void setCategory(IngredientCategory category) {
			this.category = category;
		}

		public boolean isInStoke() {
			return inStoke;
		}

		public void setInStoke(boolean inStoke) {
			this.inStoke = inStoke;
		}

		public IngredientsItem(Long id, String name, Resturant resturant, List<Food> foods) {
			this.id = id;
			this.name = name;
			this.resturant = resturant;
			this.foods = foods;
		}

		public IngredientsItem() {
			super();
		}
	    
	
	    
}
