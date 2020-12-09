package com.dh.blogging.DTO.Article;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class ArticleCreateDto {
    @NotNull
    private String title;
    @NotNull
    private String body;
    @NotNull
    private Integer owner;
}
