package com.dh.blogging.Controller;

import com.dh.blogging.DTO.Article.ArticleCreateDto;
import com.dh.blogging.DTO.Article.ArticleDto;
import com.dh.blogging.Models.Article;
import com.dh.blogging.Services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping("/articles")
    public List<ArticleDto> getArticles(){
        ModelMapper modelMapper = new ModelMapper();
        return service.getAll().stream().map(article -> {
            return modelMapper.map( article , ArticleDto.class );
        }).collect(Collectors.toList());
    }
    @GetMapping("/article/{id}")
    public ArticleDto getArticleById(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();

        Article article = service.get(id);

        return modelMapper.map(article , ArticleDto.class);
    }

    @PostMapping("/article")
    public ArticleDto createArticle(@RequestBody ArticleCreateDto articleCreateDto){
        ModelMapper modelMapper = new ModelMapper();
        Article a = modelMapper.map( articleCreateDto , Article.class );
        a.setCreatedDate(new Date());
        a.setStatus("waiting");
        a.setLikes(0);

        Article createdArticle = service.save(a);

        return modelMapper.map(createdArticle , ArticleDto.class);

    }
}
