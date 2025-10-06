package food.example.online.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import food.example.online.food.entity.IngredientsItem;

public interface IngredientsItemsRepository extends JpaRepository<IngredientsItem, Long>{

	public List<IngredientsItem> findByResturantId(Long id);
	
	Optional<IngredientsItem> findByNameAndResturantId(String name, Long resturantId);

}
