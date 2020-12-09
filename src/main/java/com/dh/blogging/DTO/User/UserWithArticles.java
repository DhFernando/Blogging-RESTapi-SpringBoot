package com.dh.blogging.DTO.User;

import com.dh.blogging.DTO.Article.ArticleDto;
import lombok.Data;

import java.util.List;

@Data
public class UserWithArticles {
    private UserDto user;
    private List<ArticleDto> articles;
}