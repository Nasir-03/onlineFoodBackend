package food.example.online.food.dto;

import java.util.List;

import food.example.online.food.entity.Address;

public class OrderRequest {

	private Long resturantId;
	private Address deliveryAddress;
	private List<OrderItemRequest> items;
	
	public List<OrderItemRequest> getItems() {
		return items;
	}
	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}
	public Long getResturantId() {
		return resturantId;
	}
	public void setResturantId(Long resturantId) {
		this.resturantId = resturantId;
	}
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
}
