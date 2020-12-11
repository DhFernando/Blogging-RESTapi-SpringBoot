package com.dh.blogapi.Controllers;

import com.dh.blogapi.DTOs.Blog.BlogCreateDto;
import com.dh.blogapi.DTOs.Blog.BlogDto;
import com.dh.blogapi.Models.Blog;
import com.dh.blogapi.Services.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class BlogController {

    @Autowired
    private BlogService service;

    @PostMapping("/blog")
    public BlogDto createArticle(@RequestBody BlogCreateDto articleCreateDto){
        ModelMapper modelMapper = new ModelMapper();
        Blog a = modelMapper.map( articleCreateDto , Blog.class );
        a.setCreatedDate(new Date());
        a.setStatus("waiting");
        a.setLikes(0);

        Blog createdArticle = service.save(a);

        return modelMapper.map(createdArticle , BlogDto.class);

    }
}
