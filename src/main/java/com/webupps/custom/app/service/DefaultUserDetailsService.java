package com.webupps.custom.app.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.webupps.custom.app.model.Users;
import com.webupps.custom.app.repository.UsersRepository;

import java.util.Collections;
import java.util.List;

public class DefaultUserDetailsService implements UserDetailsService {
	
	private final UsersRepository usersRepository;

    public DefaultUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	
        List<Users> userEntity = usersRepository.findByUsername(username);

        if (userEntity.size()>0) {
            final Users user = userEntity.get(0);

            return new User(user.getUsername(),
                    passwordNoEncoding(user),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
        }

        return null;
    }

    private String passwordNoEncoding(Users user) {
        return "{noop}" + user.getPassword();
    }
}
