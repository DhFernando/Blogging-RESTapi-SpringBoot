package com.dh.blogapi.Controllers;

import com.dh.blogapi.DTOs.Blog.BlogCreateDto;
import com.dh.blogapi.DTOs.Blog.BlogDto;
import com.dh.blogapi.Models.Blog;
import com.dh.blogapi.Services.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlogController {

    @Autowired
    private BlogService service;

    @PostMapping("/blog")
    public ResponseEntity<?> createArticle(@RequestBody BlogCreateDto articleCreateDto){
        try{
            ModelMapper modelMapper = new ModelMapper();
            Blog a = modelMapper.map( articleCreateDto , Blog.class );
            a.setCreatedDate(new Date());
            a.setStatus("waiting");
            a.setLikes(0);

            Blog createdArticle = service.save(a);

            return new ResponseEntity<>(modelMapper.map(createdArticle , BlogDto.class) , HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( "Internal Sever Error"  , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/blogs")
    public ResponseEntity<?> fetchBlogs(){
        try {
            return new ResponseEntity<>( service.getAll().stream().map( blog -> {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(blog , BlogDto.class);
            } ).collect(Collectors.toList()) , HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( "Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Integer id){
        try{
            ModelMapper modelMapper = new ModelMapper();
            return new ResponseEntity<>(modelMapper.map(service.get(id) , BlogDto.class) , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Blog Not found" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id){
        try{
            service.delete(id);
            return new ResponseEntity<>("Blog Success Fully Deleted" , HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>("Blog Not found" , HttpStatus.NOT_FOUND);
        }
    }
}
