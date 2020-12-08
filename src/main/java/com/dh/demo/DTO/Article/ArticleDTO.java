package com.dh.demo.DTO.Article;

import com.dh.demo.Models.Article;
import lombok.Data;
import lombok.Setter;

@Data
public class ArticleDTO {
    private String title;
    private String body;
    private Integer creatorId;

    public ArticleDTO(Article a){
        this.setBody(a.body );
        this.setTitle(a.title);
        this.setCreatorId(a.creatorId);
    }
}
