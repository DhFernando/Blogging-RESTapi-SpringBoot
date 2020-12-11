package com.dh.blogapi.Services;

import com.dh.blogapi.Interfaces.IUserRepository;
import com.dh.blogapi.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository repo;

    public List<User> getAll(){ return repo.findAll(); }

    public User save(User person){ return repo.save(person); }

    public User get(int id){ return repo.findById(id).get(); }

    public void delete(int id){  repo.deleteById(id); }
}
