package food.example.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import food.example.online.food.entity.OrderItems;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long>{

}
