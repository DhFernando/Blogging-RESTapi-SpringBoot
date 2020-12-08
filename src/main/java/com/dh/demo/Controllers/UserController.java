package com.dh.demo.Controllers;

import com.dh.demo.DTO.Article.ArticleDTO;
import com.dh.demo.DTO.User.UserCreateDto;
import com.dh.demo.DTO.User.UserDto;
import com.dh.demo.Models.Article;
import com.dh.demo.Models.User;
import com.dh.demo.Projection.UserProjection;
import com.dh.demo.services.ArticleService;
import com.dh.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/users")
    public List<UserProjection> getUsers() { return service.getAll(); }

    @GetMapping("/user/{id}/articles")
    public List<Article> getArticlesByUserId(@PathVariable Integer id){
        return articleService.getArticlesByUserId(id);
    }

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto ){
        User u = new User(
                userCreateDto.getUserName() , userCreateDto.getEmail() , userCreateDto.getPasswordHash() ,
                "user" , new Date() , userCreateDto.getImageUrl()  );
        User createdUser = service.save(u);
        return new UserDto(createdUser);
    }

}