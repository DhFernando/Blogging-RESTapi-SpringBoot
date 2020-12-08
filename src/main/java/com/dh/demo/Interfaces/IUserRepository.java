package com.dh.demo.Interfaces;

import com.dh.demo.Models.User;
import com.dh.demo.Projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u")
    List<UserProjection> getAllUsers();
}
