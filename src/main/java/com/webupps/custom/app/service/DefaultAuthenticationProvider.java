package com.webupps.custom.app.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.webupps.custom.app.model.Users;
import com.webupps.custom.app.repository.UsersRepository;

public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private final UsersRepository usersRepository;

    public DefaultAuthenticationProvider(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }

        if (authentication.getName().isEmpty() || authentication.getCredentials().toString().isEmpty()) {
            return null;
        }

        List<Users> appUser = this.usersRepository.findByUsername(authentication.getName());

        if (appUser.size()>0) {
            final Users user = appUser.get(0);
            final String username = authentication.getName();
            final Object providedUserPassword = authentication.getCredentials();

            if (username.equalsIgnoreCase(user.getUsername())
                    && providedUserPassword.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        passwordNoEncoding(user),
                        //Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
                        Collections.singleton(new SimpleGrantedAuthority(user.getName())));
            }
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    private String passwordNoEncoding(Users users) {
        return "{noop}" + users.getPassword();
    }
}
