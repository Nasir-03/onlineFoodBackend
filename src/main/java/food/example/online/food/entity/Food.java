package food.example.online.food.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Food {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String name;
//	
//	private String description;
//	private Long price;
//	
//	 @ManyToOne(cascade = CascadeType.PERSIST) // ✅ allows saving new category
//	   private Category foodCategory;
//	
//	@Column(length = 1000)
//	@ElementCollection
//	private List<String> images;
//	
//	private boolean available;
//	
//	@ManyToOne
//	private Resturant resturant;
//
//     private boolean isVegeterian;
//     private boolean isSeasonal;
//     
//     @ManyToMany
//     @JoinTable(
//         name = "food_ingredients",                         // join table
//         joinColumns = @JoinColumn(name = "food_id"),       // column referencing Food
//         inverseJoinColumns = @JoinColumn(name = "ingredient_id") // column referencing IngredientsItem
//     )
//     private List<IngredientsItem> ingredients = new ArrayList<>();
//
//     
//     private Date creationDate;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String description;
	    private double price;
	    
	    @Column(name = "is_vegeterian", nullable = false)
	    private boolean vegeterian;
	    
	    @Column(name = "is_seasonal", nullable = false)
	    private boolean seasonal;
	   
	    @Column(name = "is_available", nullable = false)
	    private boolean available;

	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category foodCategory;

	    @ManyToOne
	    @JoinColumn(name = "resturant_id")
	    @JsonIgnore
	    private Resturant resturant;

	    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(
	        name = "food_ingredients",
	        joinColumns = @JoinColumn(name = "food_id"),
	        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	    )
	    private List<IngredientsItem> ingredients = new ArrayList<>();

	    @ElementCollection
	    private List<String> images = new ArrayList<>();

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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
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

		public Category getFoodCategory() {
			return foodCategory;
		}

		public void setFoodCategory(Category foodCategory) {
			this.foodCategory = foodCategory;
		}

		public Resturant getResturant() {
			return resturant;
		}

		public void setResturant(Resturant resturant) {
			this.resturant = resturant;
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

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		public Food(Long id, String name, String description, double price, boolean vegeterian, boolean seasonal,
				Category foodCategory, Resturant resturant, List<IngredientsItem> ingredients, List<String> images) {
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.vegeterian = vegeterian;
			this.seasonal = seasonal;
			this.foodCategory = foodCategory;
			this.resturant = resturant;
			this.ingredients = ingredients;
			this.images = images;
		}

		public Food() {
			super();
		}
     
	    
}


























//
//package food.example.online.food.entity;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "food")
//public class Food {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @Column(columnDefinition = "TEXT")
//    private String description;
//
//    private Long price;
//
//    // boolean fields mapped properly
//    @Column(name = "is_vegeterian", nullable = false)
//    private boolean vegeterian = false;
//
//    @Column(name = "is_seasonal", nullable = false)
//    private boolean seasonal = false;
//
//    @Column(nullable = false)
//    private boolean available = true;
//
//    // Many-to-One: Food -> Category
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category foodCategory;
//
//    // Many-to-One: Food -> Restaurant
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "resturant_id", nullable = false)
//    private Resturant resturant;
//
//    // Many-to-Many: Food -> Ingredients
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//        name = "food_ingredients",
//        joinColumns = @JoinColumn(name = "food_id"),
//        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
//    )
//    private List<IngredientsItem> ingredients;
//
//    // Images stored as strings (optional)
//    @ElementCollection
//    @CollectionTable(name = "food_images", joinColumns = @JoinColumn(name = "food_id"))
//    @Column(name = "image_url")
//    private List<String> images;
//
//    // ✅ Getters and setters
//
//    public Long getId() { return id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public Long getPrice() { return price; }
//    public void setPrice(Long long1) { this.price = long1; }
//
//    public boolean isVegeterian() { return vegeterian; }
//    public void setVegeterian(boolean vegeterian) { this.vegeterian = vegeterian; }
//
//    public boolean isSeasonal() { return seasonal; }
//    public void setSeasonal(boolean seasonal) { this.seasonal = seasonal; }
//
//    public boolean isAvailable() { return available; }
//    public void setAvailable(boolean available) { this.available = available; }
//
//    public Category getFoodCategory() { return foodCategory; }
//    public void setFoodCategory(Category foodCategory) { this.foodCategory = foodCategory; }
//
//    public Resturant getResturant() { return resturant; }
//    public void setResturant(Resturant resturant) { this.resturant = resturant; }
//
//    public List<IngredientsItem> getIngredients() { return ingredients; }
//    public void setIngredients(List<IngredientsItem> ingredients) { this.ingredients = ingredients; }
//
//    public List<String> getImages() { return images; }
//    public void setImages(List<String> images) { this.images = images; }
//}
//
