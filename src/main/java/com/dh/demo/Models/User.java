package com.dh.demo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "userName")
    public String userName;

    @Column(name = "email")
    public String email;

    @Column(name = "passwordHash")
    public String passwordHash;

    @Column(name = "permissionLevel")
    public String permissionLevel;

    @Column(name = "joinedDate")
    public Date joinedDate;

    @Column(name = "imageUrl")
    public String imageUrl;

    public User(  String userName , String email ,String passwordHash , String permissionLevel , Date joinedDate , String imageUrl ){
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.permissionLevel = permissionLevel;
        this.joinedDate = joinedDate;
        this.imageUrl = imageUrl;
    }

    public User(){}

    public void setId(int id){ this.Id = id; }

}
