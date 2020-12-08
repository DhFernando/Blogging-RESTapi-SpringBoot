package com.dh.demo.Interfaces;

import com.dh.demo.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article , Integer> {

}
