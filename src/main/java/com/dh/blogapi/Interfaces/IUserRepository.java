package com.dh.blogapi.Interfaces;

import com.dh.blogapi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User , Integer> {

    @Query( value = "SELECT * FROM users WHERE userName = :userNameOrEmail OR email = :userNameOrEmail" , nativeQuery = true )
    User findUserByUserNameOrEmail( @Param("userNameOrEmail") String userNameOrEmail );
}
