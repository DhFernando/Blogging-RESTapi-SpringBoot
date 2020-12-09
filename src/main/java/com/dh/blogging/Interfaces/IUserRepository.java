package com.dh.blogging.Interfaces;

import com.dh.blogging.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User , Integer> {
}
