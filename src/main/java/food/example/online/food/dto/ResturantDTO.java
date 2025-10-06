package food.example.online.food.dto;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

@Embeddable
public class ResturantDTO {
	private Long id;
	private List<String> image;
	private String description;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public ResturantDTO(Long id, List<String> image, String description) {
		this.id = id;
		this.image = image;
		this.description = description;
	}

	public ResturantDTO() {
		super();
	}
	

}
