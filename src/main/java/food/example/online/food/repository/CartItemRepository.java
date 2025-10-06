package food.example.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import food.example.online.food.entity.CartItems;

public interface CartItemRepository extends JpaRepository<CartItems, Long>{

}
