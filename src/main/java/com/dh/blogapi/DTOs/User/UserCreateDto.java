package com.dh.blogapi.DTOs.User;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {
    @NotNull
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String passwordHash;
    private String imageUrl;
}

