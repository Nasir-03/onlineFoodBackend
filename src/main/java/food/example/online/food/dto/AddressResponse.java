// AddressResponse.java
package food.example.online.food.dto;

public class AddressResponse {
	  private Long id;
	    private String city;
	    private String street;
	    private Integer pinCode;
	    private String state;
	    
		public AddressResponse(Long id, String city, String street, Integer pinCode, String state) {
			this.id = id;
			this.city = city;
			this.street = street;
			this.pinCode = pinCode;
			this.state = state;
		}
		public AddressResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public Integer getPinCode() {
			return pinCode;
		}
		public void setPinCode(Integer pinCode) {
			this.pinCode = pinCode;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
	    
	    
}
