package food.example.online.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import food.example.online.food.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByCustomerId(Long customerid);
	
	public List<Order> findByResturantId(Long resturantId);

	 @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.resturant.id = :resturantId")
	    List<Order> findByResturantIdWithItems(@Param("resturantId") Long resturantId);

	    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.customer.id = :customerId")
	    List<Order> findByCustomerIdWithItems(@Param("customerId") Long customerId);
	    
    }
