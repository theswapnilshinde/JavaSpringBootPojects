
package com.verizon.authentication.controllerService.service;

import java.util.ArrayList;

import com.verizon.authentication.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    com.verizon.authentication.config.Authentication auth;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserData userData = auth.getUser(username);
            if (userData != null) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                return new User(username, passwordEncoder.encode(userData.getPassword()),
                        new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }catch (Exception exception){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}