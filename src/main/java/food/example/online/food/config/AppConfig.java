package food.example.online.food.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

//@Configuration
//@EnableWebSecurity
//public class AppConfig {
//	
//	private final JwtTokenValidator jwtTokenValidator;
//
//    public AppConfig(JwtTokenValidator jwtTokenValidator) {
//        this.jwtTokenValidator = jwtTokenValidator;
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(auth -> auth
//            		.requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
//            	.requestMatchers("/api/resturants/getAll").permitAll()
//                .requestMatchers("/api/admin/resturant/**").hasRole("RESTURANT_OWNER")
//                .requestMatchers("/api/admin/**").hasAnyRole("RESTURANT_OWNER", "ADMIN")
//                .requestMatchers("/api/**").authenticated()
//                .anyRequest().permitAll()
//            )
//            .addFilterBefore(jwtTokenValidator, BasicAuthenticationFilter.class)
//            .csrf(csrf -> csrf.disable())
//            .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//
//        return http.build(); // ✅ must return http.build()
//    }
//
//    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of(
//        		"http://localhost:5173",
//        		"http://localhost:5174",
//        		"https://online-food-front-end-6x79.vercel.app"   // Vercel deploy link
//        		));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true);
//        config.setMaxAge(3600L);
//        return config;
//    }
//
//    // ✅ Expose CorsConfigurationSource bean
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        return request -> getCorsConfiguration(request);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

@Configuration
@EnableWebSecurity
public class AppConfig {

	private final JwtTokenValidator jwtTokenValidator;

	public AppConfig(JwtTokenValidator jwtTokenValidator) {
		this.jwtTokenValidator = jwtTokenValidator;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
						.requestMatchers("/api/resturants/getAll").permitAll()
						.requestMatchers("/api/admin/resturant/**").hasRole("RESTURANT_OWNER")
						.requestMatchers("/api/admin/**").hasAnyRole("RESTURANT_OWNER", "ADMIN")
						.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
				.addFilterBefore(jwtTokenValidator, BasicAuthenticationFilter.class).csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()));

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		// ✅ Allow localhost in dev and your Vercel frontend
		config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5174",
				"https://online-food-front-end-6x79.vercel.app"));

		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);
		config.setMaxAge(3600L);

		return request -> config;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
