package com.dh.blogging.Models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @Column(name = "owner")
    private Integer owner;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "status")
    private String status;
    @Column(name = "likes")
    private Integer likes;
}
