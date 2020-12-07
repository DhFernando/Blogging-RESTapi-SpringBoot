package com.dh.demo.Controllers;

import com.dh.demo.Models.User;
import com.dh.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getUsers() { return service.getAll(); }

}