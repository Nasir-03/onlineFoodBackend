package food.example.online.food.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import food.example.online.food.dto.ContactInformation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resturant")
public class Resturant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonBackReference
	private User owner;

	private String name;
	private String description;
	private String cuisineType;
	
//	@OneToOne
//	private Address address;
	
	@Embedded
	private ContactInformation contactInformation;
	
	private String openingHours;
	
//	@OneToMany(mappedBy = "resturant",cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	@Column(length = 1000)
	private List<String> images;
	
	private LocalDateTime registerationDate;
	private boolean open = true;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "resturant",cascade = CascadeType.ALL)
//	private List<Food> foods = new ArrayList<>();
	
	@OneToMany(mappedBy = "resturant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Category> categories = new ArrayList<>();

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(mappedBy = "resturant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Food> foods = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "resturant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public Resturant(Long id, User owner, String name, String description, String cuisineType, Address address,
			ContactInformation contactInformation, String openingHours, List<Order> orders, List<String> images,
			LocalDateTime registerationDate, boolean open, List<Food> foods,List<Category> categories) {
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.description = description;
		this.cuisineType = cuisineType;
		this.address = address;
		this.contactInformation = contactInformation;
		this.openingHours = openingHours;
		this.orders = orders;
		this.images = images;
		this.registerationDate = registerationDate;
		this.open = open;
		this.foods = foods;
		this.categories = categories;
	}

	public Resturant() {
		super();
	}
}
