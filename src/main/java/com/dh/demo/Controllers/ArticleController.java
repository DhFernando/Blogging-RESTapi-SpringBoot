package com.dh.demo.Controllers;

import com.dh.demo.DTO.ArticleDTO;
import com.dh.demo.Models.Article;
import com.dh.demo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping("/articles")
    public List<ArticleDTO> getArticles(){

        return service.getAll().stream().map(ArticleDTO::new).collect(Collectors.toList());
    }



}
