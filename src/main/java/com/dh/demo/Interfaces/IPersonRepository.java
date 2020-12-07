package com.dh.demo.Interfaces;

import com.dh.demo.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Integer>  {

}