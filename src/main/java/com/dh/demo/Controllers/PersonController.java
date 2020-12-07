package com.dh.demo.Controllers;

import com.dh.demo.Models.Person;
import com.dh.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
	@Autowired
	private PersonService service;

	@GetMapping("/persons")
	public List<Person> getPersons() { return service.getAll(); }

	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable Integer id){
		return service.get(id);
	}

	@PostMapping("person")
	public void addPerson(Person p){
		service.save(p);
	}

	@DeleteMapping("person/{id}")
	public void deletePerson(@PathVariable Integer id){
		service.delete(id);
	}
}