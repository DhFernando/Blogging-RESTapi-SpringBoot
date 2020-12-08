package com.dh.demo.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "permissionLevel")
    private String permissionLevel;

    @Column(name = "joinedDate")
    private Date joinedDate;

    @Column(name = "imageUrl")
    private String imageUrl;

    @OneToMany(mappedBy = "creatorId")
    public List<Article> articles = new ArrayList<>();

    public User(  String userName , String email ,String passwordHash , String permissionLevel , Date joinedDate , String imageUrl ){
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.permissionLevel = permissionLevel;
        this.joinedDate = joinedDate;
        this.imageUrl = imageUrl;
    }

    public User(){}
}
