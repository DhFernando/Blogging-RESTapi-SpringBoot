package com.dh.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person , Integer>  {

}
