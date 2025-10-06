// OrderResponse.java
package food.example.online.food.dto;

import java.util.Date;
import java.util.List;

import food.example.online.food.entity.User;

public class OrderResponse {
    private Long id;
    private String orderStatus;
    private Date createAt;
    private double totalPrice;
    private int totalItem;
    private AddressResponse deliveryAddress;
    private List<OrderItemResponse> orderItems;
    
    private CustomerResponse customer;
    
	public CustomerResponse getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResponse customer) {
		this.customer = customer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	public AddressResponse getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(AddressResponse deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public List<OrderItemResponse> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemResponse> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderResponse(Long id, String orderStatus, Date createAt, double totalPrice, int totalItem,
			AddressResponse deliveryAddress, List<OrderItemResponse> orderItems,CustomerResponse customer) {
		this.id = id;
		this.orderStatus = orderStatus;
		this.createAt = createAt;
		this.totalPrice = totalPrice;
		this.totalItem = totalItem;
		this.deliveryAddress = deliveryAddress;
		this.orderItems = orderItems;
		this.customer = customer;
	}

	public OrderResponse() {
		super();
	}
}
