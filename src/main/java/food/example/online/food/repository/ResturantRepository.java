package food.example.online.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;

public interface ResturantRepository extends JpaRepository<Resturant, Long> {

	@Query("SELECT r FROM Resturant r " +
		       "WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) " +
		       "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
	List<Resturant> findBySearchQuery(String query);

	Resturant findByOwner(User owner);
	
	@Query("SELECT DISTINCT r FROM Resturant r WHERE r.owner.id = :userId")
	    List<Resturant> findByOwnerId(Long userId);
	 
	 @Query("SELECT r FROM User u JOIN u.favorites r WHERE u.id = :userId")
	 List<Resturant> findFavoritesByUserId(@Param("userId") Long userId);

}
