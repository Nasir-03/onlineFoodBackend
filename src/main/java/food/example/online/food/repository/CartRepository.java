package food.example.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import food.example.online.food.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	public Cart findByCustomerId(Long id);
}
