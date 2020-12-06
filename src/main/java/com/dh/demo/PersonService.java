package com.dh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private IPersonRepository repo;

    public List<Person> getAll(){
        return repo.findAll();
    }
    public void save(Person person){
        repo.save(person);
    }

    public Person get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }
}
