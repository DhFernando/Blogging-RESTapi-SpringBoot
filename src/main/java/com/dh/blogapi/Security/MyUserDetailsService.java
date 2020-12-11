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
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String Id) throws UsernameNotFoundException {

        com.dh.blogapi.Models.User u =  userService.get(Integer.parseInt(Id));

        return new User( String.valueOf(u.getId()) ,  u.getPasswordHash()  ,  new ArrayList<>());
    }

    public UserDetails getUserById( Integer Id ) throws UsernameNotFoundException {

        com.dh.blogapi.Models.User u =  userService.get(Id);

        return new User( String.valueOf(u.getId()) ,  u.getPasswordHash()  ,  new ArrayList<>());
    }
}
