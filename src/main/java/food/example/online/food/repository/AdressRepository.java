package food.example.online.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import food.example.online.food.entity.Address;

public interface AdressRepository extends JpaRepository<Address, Long>{

}
