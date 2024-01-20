package uz.pdp.project.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.project.domains.AuthUser;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final AuthUser authUser;
    private final Set<GrantedAuthority> authorities;

    public CustomUserDetails(AuthUser authUser, Set<GrantedAuthority> authorities) {
        this.authUser = authUser;
        this.authorities = authorities;
    }
    public AuthUser getAuthUser() {
        return authUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
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
}
