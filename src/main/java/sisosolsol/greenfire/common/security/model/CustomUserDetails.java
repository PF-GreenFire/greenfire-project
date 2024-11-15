package sisosolsol.greenfire.common.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
public class CustomUserDetails implements UserDetails {
    private final UUID id;
    private final String email;
    private final SimpleGrantedAuthority authority;

    public CustomUserDetails(SupabaseUserDTO supabaseUserDTO) {
        this.id = supabaseUserDTO.getId();
        this.email = supabaseUserDTO.getEmail();
        this.authority = new SimpleGrantedAuthority(supabaseUserDTO.getRole().getAuthority());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() { return null; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    public String getRoleName() {
        return getAuthorities().stream()
                .findFirst()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .orElse("USER");
    }
}