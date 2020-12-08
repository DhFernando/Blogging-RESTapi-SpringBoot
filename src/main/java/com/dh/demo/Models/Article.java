package com.dh.demo.Models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="articles")
public class Article {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "title")
    public String title;
    @Column(name = "body")
    public String body;
    @Column(name = "creatorId")
    public Integer creatorId;
    @Column(name = "createdDate")
    public Date createdDate;
    @Column(name = "status")
    public String status;
    @Column(name = "likes")
    public Integer likes;

    public Article(String title , String body , Integer creatorId , Date createdDate , String status , Integer likes ){
        this.body = body;
        this.title = title;
        this.createdDate = createdDate;
        this.likes = likes;
        this.creatorId =creatorId;
        this.status = status;
    }

    public Article(){}

    public void setId(Integer id){ this.Id = id; }

}
