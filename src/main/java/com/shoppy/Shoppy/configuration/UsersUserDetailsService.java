package com.shoppy.Shoppy.configuration;

import com.shoppy.Shoppy.entity.Users;
import com.shoppy.Shoppy.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsersUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository _usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=_usersRepository.findByUsername(username);
        return new UsersUserDetails(users);
    }
}
