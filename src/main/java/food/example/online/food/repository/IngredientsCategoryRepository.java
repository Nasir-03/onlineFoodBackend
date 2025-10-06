package food.example.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import food.example.online.food.entity.IngredientCategory;

public interface IngredientsCategoryRepository extends JpaRepository<IngredientCategory, Long>{

	public List<IngredientCategory> findByResturantId(Long id);
}
