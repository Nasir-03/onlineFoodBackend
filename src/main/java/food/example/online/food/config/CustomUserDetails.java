package food.example.online.food.config;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import food.example.online.food.entity.UserRole;


public class CustomUserDetails implements UserDetails {

    private Long id;
    private String email;      // used as login username
    private String fullName;   // actual name of the user
    private String password;
    private UserRole role;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String email, String fullName,
                             String password, UserRole role,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;  // ✅ Spring uses email as username
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // custom getters
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getRole() {
        return role;
    }
}
