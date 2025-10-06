package food.example.online.food.config;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenValidator extends OncePerRequestFilter{
	
	private JwtUtill jwtUtill;
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	public JwtTokenValidator(JwtUtill jwtUtill, MyUserDetailsService myUserDetailsService) {
		this.jwtUtill = jwtUtill;
		this.myUserDetailsService = myUserDetailsService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {

	    String header = request.getHeader("Authorization");
	    String token = null;

	    if (header != null && header.startsWith("Bearer ")) {
	        token = header.substring(7);
	    }

	    if (token != null) {
	        try {
	            String username = jwtUtill.getUserNameFromToken(token);

	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

	                if (jwtUtill.validateToken(token, userDetails.getUsername())) {
	                    UsernamePasswordAuthenticationToken authToken =
	                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("JWT validation failed: " + e.getMessage());
	        }
	    }

	    filterChain.doFilter(request, response);
	}


}
