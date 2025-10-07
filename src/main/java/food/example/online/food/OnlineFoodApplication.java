package food.example.online.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class OnlineFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodApplication.class, args);
	}
	
	@PostConstruct
    public void checkEnv() {
        System.out.println("ENV STRIPE_API_KEY = " + System.getenv("STRIPE_API_KEY"));
    }
}
