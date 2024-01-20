package uz.pdp.project.configuration.security;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.project.daos.AuthUserDao;
import uz.pdp.project.domains.AuthUser;

import java.util.Collections;
import java.util.Set;

@Component
@ComponentScan("uz.pdp.project")
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserDao authUserDao;

    public CustomUserDetailsService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser=authUserDao.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("user not found {%}".formatted(username))
                );
        String role="ROLE_"+authUser.getRole();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        Set<GrantedAuthority> authorities= (Set<GrantedAuthority>) Collections.singletonList(grantedAuthority);
        return new CustomUserDetails(authUser,authorities);
    }
}
