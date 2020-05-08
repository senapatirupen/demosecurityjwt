package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.entity.User;
import com.example.demosecurityjwt.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EcomUserService ecomUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = ecomUserService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
        }
        // Create a granted authority based on user's role.
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRole : user.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        // Create a UserDetails object from the data
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);

        return userDetails;
    }


}
