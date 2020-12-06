package com.dh.demo;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    public String Name;

    @Column(name = "Age")
    public Integer Age;

    @Column(name = "Department")
    public String Department;

    public Person(Integer id , String Name , Integer Age , String Department){
        this.Age = Age;
        this.Name = Name;
        this.Department = Department;
    }

    public Person(){

    }


    public void setId(int id){ this.id = id; }


}
