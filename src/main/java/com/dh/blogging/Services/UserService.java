package com.dh.blogging.Services;

import com.dh.blogging.Interfaces.IUserRepository;
import com.dh.blogging.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
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
