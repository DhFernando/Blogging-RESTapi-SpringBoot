package com.dh.blogapi.Services;

import com.dh.blogapi.Interfaces.IBlogRepository;
import com.dh.blogapi.Models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private IBlogRepository repo;

    public List<Blog> getAll(){ return repo.findAll(); }

    public Blog save(Blog blog){ return repo.save(blog); }

    public Blog get(int id){ return repo.findById(id).get(); }

    public void delete(int id){  repo.deleteById(id); }
}
