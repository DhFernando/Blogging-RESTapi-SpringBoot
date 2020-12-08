package com.dh.demo.Interfaces;

import com.dh.demo.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IArticleRepository extends JpaRepository<Article , Integer> {
    @Query(value =  "select * from articles where creatorId = :id" , nativeQuery = true)
    List<Article> findArticlesByUserId(Integer id);
}
