package com.dh.blogging.DTO.Article;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ArticleDto {
    private Integer Id;
    @NotNull
    private String title;
    @NotNull
    private String body;
    @NotNull
    private Integer owner;
    private Date createdDate;
    private String status;
    private Integer likes;
}
