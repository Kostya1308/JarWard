package by.home.jarward.web.authentication;

import by.home.jarward.jar.entity.User;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFromBase = userService.getByLogin(username);
        AtomicReference<String> login = new AtomicReference<>();
        AtomicReference<String> password = new AtomicReference<>();
        AtomicReference<String> role = new AtomicReference<>();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority authority;
        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;

        userFromBase.ifPresent(item -> {
            login.set(item.getLogin());
            password.set(new String(item.getPassword()));
            role.set("ROLE_" + item.getRole().getName());
        });

        authority = new SimpleGrantedAuthority(role.get());
        grantedAuthorities.add(authority);

        return new org.springframework.security.core.userdetails.User(login.get(), password.get(),
                enabled, accountNotExpired, credentialsNotExpired, accountNotLocked, grantedAuthorities);
    }
}
