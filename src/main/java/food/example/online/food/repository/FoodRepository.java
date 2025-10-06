package food.example.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import food.example.online.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

	List<Food> findByResturantId(Long resturantId);
	
	@Query("SELECT f FROM Food f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.foodCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Food> searchFood(@Param("keyword") String keyword);

	
}
