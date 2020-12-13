package com.dh.blogapi.Controllers;

import com.dh.blogapi.DTOs.Blog.BlogCreateDto;
import com.dh.blogapi.DTOs.Blog.BlogDto;
import com.dh.blogapi.Models.Blog;
import com.dh.blogapi.Services.BlogService;
import com.dh.blogapi.Utility.JwtDecodeService;
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
    private JwtDecodeService jwtDecodeService;

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
            a.setOwner(jwtDecodeService.decode().getUsername());

            Blog createdArticle = service.save(a);

            return new ResponseEntity<>(modelMapper.map(createdArticle , BlogDto.class) , HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( "Internal Sever Error"  , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/blogs")
    public ResponseEntity<?> fetchBlogsByConditions(@RequestBody String status){

        if( jwtDecodeService.decode() != null ) {
            if(jwtDecodeService.decode().getPermissionLevel() == "user"){
                status = "approved";
            }
        }else{
            status = "approved";
        }

        try {
            return new ResponseEntity<>( service.getBlogsByStatus(status).stream().map( blog -> {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(blog , BlogDto.class);
            } ).collect(Collectors.toList()) , HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( "Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PostMapping("blog/{id}/setStatus")
    public ResponseEntity<?> setStatus(@PathVariable Integer id , @RequestBody String status ){
        if(jwtDecodeService.decode().getPermissionLevel() == "admin"){
            try{
                Blog b = service.get(id);
                service.setStatus(b.getId() , status);
            }catch (Exception e){
                return new ResponseEntity<>("Blog Not Found" , HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>("Permission denied" , HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Integer id){
        try{

            String permissionLevel = jwtDecodeService.decode().getPermissionLevel();

            ModelMapper modelMapper = new ModelMapper();
            try {
                Blog b = service.get(id);

                if(b.getStatus() == "approved" ){
                    return new ResponseEntity<>(modelMapper.map( b , BlogDto.class) , HttpStatus.OK);
                }else if(permissionLevel == "user"){
                    return new ResponseEntity<>(modelMapper.map( b , BlogDto.class) , HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Permission Denied" , HttpStatus.FORBIDDEN);
                }
            }catch (Exception e){
                return new ResponseEntity<>("Blog Not Fount" , HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            return new ResponseEntity<>("Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("blogs/{username}")
    public ResponseEntity<?> getBlogsByUsername(@PathVariable String username){
        List<Blog>  blogs = service.getBlogsByUsername(username);
        String permissionLevel = null;
        if( jwtDecodeService.decode() != null ) {
            permissionLevel = jwtDecodeService.decode().getPermissionLevel();
        }

        return new ResponseEntity<>(service.getBlogsByUsername(username).stream().map(blog -> {
            if(jwtDecodeService.decode() != null){
                if(jwtDecodeService.decode().getPermissionLevel() == "admin"){
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(blog , BlogDto.class);
                }else if(blog.getStatus() == "approved"){
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(blog , BlogDto.class);
                }
                else{
                    return  null;
                }
            }else{
                if(blog.getStatus() == "approved"){
                    ModelMapper modelMapper = new ModelMapper();
                    return modelMapper.map(blog , BlogDto.class);
                }else{
                    return  null;
                }
            }

        }).collect(Collectors.toList()) , HttpStatus.OK);
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
