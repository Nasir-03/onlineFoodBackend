package food.example.online.food.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import food.example.online.food.entity.OtpVerification;

public interface OtpRepository extends JpaRepository<OtpVerification, Long> {
	// Get the latest OTP for an email
	Optional<OtpVerification> findTopByEmailOrderByExpiryTimeDesc(String email);
}
