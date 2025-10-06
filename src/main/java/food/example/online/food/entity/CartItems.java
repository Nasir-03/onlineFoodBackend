package food.example.online.food.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//
//@Entity
//public class CartItems {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@JsonIgnore
//	@ManyToOne
//	private Cart cart;
//	
//	@ManyToOne
//	private Food food;
//	
//	private int quantity;
//	
//	private List<String> ingredients;
//	
//	private Long totalPrice;

@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Food food;

    private int quantity;

    @ElementCollection               // <<--- ADD THIS
    private List<String> ingredients;

    private double totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "resturant_id") // <<< FIX
    private Resturant resturant;

    // getters/setters...


	public Resturant getResturant() {
		return resturant;
	}

	public void setResturant(Resturant resturant) {
		this.resturant = resturant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double d) {
		this.totalPrice = d;
	}

	public CartItems(Long id, Cart cart, Food food, int quantity, List<String> ingredients, double totalPrice) {
		this.id = id;
		this.cart = cart;
		this.food = food;
		this.quantity = quantity;
		this.ingredients = ingredients;
		this.totalPrice = totalPrice;
	}

	public CartItems() {
		super();
	}
}
