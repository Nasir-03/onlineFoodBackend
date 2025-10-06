package food.example.online.food.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User customer;
	private double total;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItems> listItems = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double d) {
		this.total = d;
	}

	public List<CartItems> getListItems() {
		return listItems;
	}

	public void setListItems(List<CartItems> listItems) {
		this.listItems = listItems;
	}

	public Cart(Long id, User customer, double total, List<CartItems> listItems) {
		this.id = id;
		this.customer = customer;
		this.total = total;
		this.listItems = listItems;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


}
