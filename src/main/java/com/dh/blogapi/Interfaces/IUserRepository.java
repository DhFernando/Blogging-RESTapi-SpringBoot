package com.dh.blogapi.Interfaces;

import com.dh.blogapi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User , Integer> {
}
