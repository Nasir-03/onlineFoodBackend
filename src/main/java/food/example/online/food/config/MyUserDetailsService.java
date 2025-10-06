package food.example.online.food.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import food.example.online.food.entity.User;
import food.example.online.food.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
                
           if (user == null) {
               throw new UsernameNotFoundException("user not found");	   
           }
        
        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),      // ✅ email goes here
                user.getFullName(),   // ✅ fullName goes here
                user.getPassword(),
                user.getRole(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
            );
    }
}
