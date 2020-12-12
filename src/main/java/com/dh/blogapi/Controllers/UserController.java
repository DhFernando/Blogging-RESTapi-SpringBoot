package com.dh.blogapi.Controllers;

import com.dh.blogapi.DTOs.User.UserCreateDto;
import com.dh.blogapi.DTOs.User.UserDto;
import com.dh.blogapi.Errors.ApiError;
import com.dh.blogapi.Models.User;
import com.dh.blogapi.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        ModelMapper modelMapper = new ModelMapper();

        return service.getAll().stream().map(user ->{
            return modelMapper.map(user , UserDto.class);
        }).collect(Collectors.toList()) ;
    }

//    @GetMapping("/user/{id}/articles")
//    public UserWithArticles getArticlesByUserId(@PathVariable Integer id){
//        ModelMapper modelMapper = new ModelMapper();
//
//        UserWithArticles obj = new UserWithArticles();
//        obj.setUser( modelMapper.map(service.get(id) , UserDto.class) );
//
//        List<Article> articles = articleService.getArticlesByUserId(id);
//        obj.setArticles(articles.stream().map(article -> {
//            return modelMapper.map(article , ArticleDto.class);
//        }).collect(Collectors.toList()));
//        return obj ;
//    }

    @GetMapping("user/{username}")
    public UserDto getUser(@PathVariable String username){
        ModelMapper modelMapper = new ModelMapper();
        User u = service.getUser(username);
        return  modelMapper.map(u , UserDto.class);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userCreateDto ){


        boolean res = service.isUsernameOrEmailAlreadyTaken(userCreateDto.getUsername() , userCreateDto.getEmail());
        if(res == !true){
            ModelMapper modelMapper = new ModelMapper();

            User u = modelMapper.map(userCreateDto , User.class);

            u.setJoinedDate(new Date()) ;
            u.setPermissionLevel("user");
            u.setPasswordHash(passwordEncoder.encode(userCreateDto.getPassword()));

            User createdUser = service.save(u);

            return new ResponseEntity<>(modelMapper.map(createdUser , UserDto.class) , HttpStatus.OK ) ;
        }
         return new ResponseEntity<>( "username Or email Already Exist" , HttpStatus.CONFLICT );
    }

    // -> Error Handling <- \\

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchElementException(NoSuchElementException exception , HttpServletRequest request){
        ApiError error = new ApiError(404 , "user not found" , new Date().getTime() , request.getServletPath());
        return error;
    }
}