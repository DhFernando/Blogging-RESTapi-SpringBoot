package com.dh.demo.Controllers;

import com.dh.demo.Models.Article;
import com.dh.demo.Models.User;
import com.dh.demo.Projection.UserProjection;
import com.dh.demo.services.ArticleService;
import com.dh.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}