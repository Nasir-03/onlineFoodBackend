package food.example.online.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import food.example.online.food.entity.Category;
import food.example.online.food.entity.Resturant;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findByResturantId(Long id);

	Optional<Category> findByName(String name);

	Optional<Category> findByNameAndResturant(String name, Resturant resturant);
}
