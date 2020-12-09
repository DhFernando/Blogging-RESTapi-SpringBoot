package com.dh.blogging.DTO.User;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class UserDto {

    private Integer Id;
    private String userName;
    private String email;
    private String permissionLevel;
    private Date joinedDate;
    private String imageUrl;
    private Integer testInt;
}
