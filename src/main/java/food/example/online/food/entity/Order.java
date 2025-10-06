package food.example.online.food.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore
	@ManyToOne
	private Resturant resturant;
	
	private Long totalAmount;
	private String orderStatus;
	private Date createAt;
	
	@ManyToOne
	private Address deliveryAddress;
	
//	@OneToMany
//	private List<OrderItems> orderItems;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItems> orderItems = new ArrayList<>();
	
//	private Payment payment
	
	private int totalItem;
	
	private double totalPrice;

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

	public Resturant getResturant() {
		return resturant;
	}

	public void setResturant(Resturant resturant) {
		this.resturant = resturant;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double d) {
		this.totalPrice = d;
	}

	public Order(Long id, User customer, Resturant resturant, double totalAmount, String orderStatus, Date createAt,
			Address deliveryAddress, List<OrderItems> orderItems, int totalItem, Long totalPrice) {
		this.id = id;
		this.customer = customer;
		this.resturant = resturant;
		this.totalAmount = (long) totalAmount;
		this.orderStatus = orderStatus;
		this.createAt = createAt;
		this.deliveryAddress = deliveryAddress;
		this.orderItems = orderItems;
		this.totalItem = totalItem;
		this.totalPrice = totalPrice;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
