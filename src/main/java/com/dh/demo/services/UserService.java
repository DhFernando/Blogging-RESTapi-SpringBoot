package com.dh.demo.services;

import com.dh.demo.Interfaces.IUserRepository;
import com.dh.demo.Models.User;
import com.dh.demo.Projection.UserProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository repo;

    public List<UserProjection> getAll(){ return repo.getAllUsers(); }

    public User save(User person){ return repo.save(person); }

    public User get(int id){ return repo.findById(id).get(); }

    public void delete(int id){  repo.deleteById(id); }
}
