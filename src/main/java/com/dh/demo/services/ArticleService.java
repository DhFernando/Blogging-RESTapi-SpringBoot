package com.dh.demo.services;

import com.dh.demo.Interfaces.IArticleRepository;
import com.dh.demo.Models.Article;
import com.dh.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private IArticleRepository repo;

    public List<Article> getAll(){  return repo.findAll(); }

    public void save(Article article){
        repo.save(article);
    }

    public Article get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }

    public List<Article> getArticleByUserId(Integer id){
        return repo.getArticleByUserIdx(id);
    }
}
