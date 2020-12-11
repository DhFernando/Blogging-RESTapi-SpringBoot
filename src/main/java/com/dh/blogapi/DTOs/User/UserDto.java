package com.dh.blogapi.DTOs.User;

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
}
