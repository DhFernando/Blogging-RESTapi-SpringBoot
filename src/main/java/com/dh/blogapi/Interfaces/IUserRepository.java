package com.dh.blogapi.Interfaces;

import com.dh.blogapi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User , Integer> {

    @Query( value = "SELECT * FROM users WHERE username = :usernameOrEmail OR email = :usernameOrEmail" , nativeQuery = true )
    User findUserByUsernameOrEmail( @Param("usernameOrEmail") String usernameOrEmail );

    @Query( value = "SELECT * FROM users WHERE username = :username" , nativeQuery = true )
    User findUserByUsername( @Param("username") String username );

    @Query(value = "SELECT COUNT(1) FROM users WHERE username = :username OR email = :emil" , nativeQuery = true)
    Integer findUsernameOrEmailAlreadyTaken(@Param("username") String username , @Param("emil") String emil );

    @Query(value = "SELECT users SET permissionLevel = :setPermissionAs WHERE username = :username" , nativeQuery = true)
    void updatePermission( @Param("username") String username,@Param("setPermissionAs") String setPermissionAs);
}
