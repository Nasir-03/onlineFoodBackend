package food.example.online.food.dto;

import food.example.online.food.entity.Address;

public class OrderRequest {

	private Long resturantId;
	private Address deliveryAddress;
	
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
