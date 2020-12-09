package com.dh.blogging.Services;

import com.dh.blogging.Interfaces.IArticleRepository;
import com.dh.blogging.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private IArticleRepository repo;

    public List<Article> getAll(){  return repo.findAll(); }

    public Article save(Article article){ return repo.save(article); }

    public Article get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }

    public List<Article> getArticlesByUserId(Integer id){
        return repo.findArticlesByUserId(id);
    }
}
