package com.dh.blogapi.Security;

import com.dh.blogapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class AuthUserDetails implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // load user
        com.dh.blogapi.Models.User u =  userService.getUserByUserNameOrEmail(usernameOrEmail);
        // authenticated User create
        return new User( u.getUsername() ,  u.getPasswordHash()  ,  new ArrayList<>());
    }
}
