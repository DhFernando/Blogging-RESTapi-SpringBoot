package com.dh.blogapi.Controllers;

import com.dh.blogapi.DTOs.Blog.BlogCreateDto;
import com.dh.blogapi.DTOs.Blog.BlogDto;
import com.dh.blogapi.Models.Blog;
import com.dh.blogapi.Services.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/blogs")
    public List<BlogDto> fetchBlogs(){
        return service.getAll().stream().map( blog -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(blog , BlogDto.class);
        } ).collect(Collectors.toList());
    }

    @GetMapping("/blog/{id}")
    public BlogDto getBlogById(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(service.get(id) , BlogDto.class);
    }

    @DeleteMapping("blog/{id}")
    public void deleteBlog(@PathVariable Integer id){
        service.delete(id);
    }
}
