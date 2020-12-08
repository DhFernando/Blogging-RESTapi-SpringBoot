package com.dh.demo.DTO.User;

import com.dh.demo.Models.User;
import lombok.Data;

import java.util.Date;
@Data
public class UserDto {
    private Integer Id;
    private String userName;
    private String email;
    private String permissionLevel;
    private Date joinedDate;
    private String imageUrl;

    public UserDto(User u){
        this.setId(u.getId() );
        this.setUserName(u.getUserName());
        this.setEmail(u.getEmail());
        this.setEmail(u.getEmail());
        this.setJoinedDate(u.getJoinedDate());
        this.setImageUrl(u.getImageUrl());
        this.setPermissionLevel(u.getPermissionLevel());
    }
}
