package food.example.online.food.dto;

import java.time.LocalDateTime;
import java.util.List;

import food.example.online.food.entity.UserRole;

public class ResturantResponseDTO {

	 private Long id;
	    private String name;
	    private String description;
	    private String cuisineType;
	    private ContactInformation contactInformation;
	    private String openingHours;
	    private List<String> images;
	    private LocalDateTime registerationDate;
	    private boolean open;

	    // owner fields we want to expose
	    private String ownerName;
	    private String ownerEmail;
	    private UserRole ownerRole;
	    
	    // Adress field
	    private AddressResponse address;
	    
		public AddressResponse getAddress() {
			return address;
		}
		public void setAddress(AddressResponse address) {
			this.address = address;
		}
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
		public String getCuisineType() {
			return cuisineType;
		}
		public void setCuisineType(String cuisineType) {
			this.cuisineType = cuisineType;
		}
		public ContactInformation getContactInformation() {
			return contactInformation;
		}
		public void setContactInformation(ContactInformation contactInformation) {
			this.contactInformation = contactInformation;
		}
		public String getOpeningHours() {
			return openingHours;
		}
		public void setOpeningHours(String openingHours) {
			this.openingHours = openingHours;
		}
		public List<String> getImages() {
			return images;
		}
		public void setImages(List<String> images) {
			this.images = images;
		}
		public LocalDateTime getRegisterationDate() {
			return registerationDate;
		}
		public void setRegisterationDate(LocalDateTime registerationDate) {
			this.registerationDate = registerationDate;
		}
		public boolean isOpen() {
			return open;
		}
		public void setOpen(boolean open) {
			this.open = open;
		}
		public String getOwnerName() {
			return ownerName;
		}
		public void setOwnerName(String ownerName) {
			this.ownerName = ownerName;
		}
		public String getOwnerEmail() {
			return ownerEmail;
		}
		public void setOwnerEmail(String ownerEmail) {
			this.ownerEmail = ownerEmail;
		}
		public UserRole getOwnerRole() {
			return ownerRole;
		}
		public void setOwnerRole(UserRole userRole) {
			this.ownerRole = userRole;
		}
		public ResturantResponseDTO(Long id, String name, String description, String cuisineType,
				ContactInformation contactInformation, String openingHours, List<String> images,
				LocalDateTime registerationDate, boolean open, String ownerName, String ownerEmail, UserRole ownerRole,AddressResponse address) {
			this.id = id;
			this.name = name;
			this.description = description;
			this.cuisineType = cuisineType;
			this.contactInformation = contactInformation;
			this.openingHours = openingHours;
			this.images = images;
			this.registerationDate = registerationDate;
			this.open = open;
			this.ownerName = ownerName;
			this.ownerEmail = ownerEmail;
			this.ownerRole = ownerRole;
			this.address = address;
		}
		public ResturantResponseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}  
	    
	    
    
}
