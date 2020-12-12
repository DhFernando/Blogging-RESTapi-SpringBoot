package com.dh.blogapi.Controllers;

import com.dh.blogapi.DTOs.JWT.JwtRequest;
import com.dh.blogapi.DTOs.JWT.JwtResponse;
import com.dh.blogapi.Security.AuthUserDetails;
import com.dh.blogapi.Utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthUserDetails authUserDetails;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home(){
        return "Welcome Security";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try{
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsernameOrEmail(),
                    jwtRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw  new Exception("username password error" , e);
        }

        final UserDetails userDetails = authUserDetails.loadUserByUsername(jwtRequest.getUsernameOrEmail());
        final String token = jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }

}
