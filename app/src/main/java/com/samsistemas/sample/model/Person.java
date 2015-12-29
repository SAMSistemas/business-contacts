package com.samsistemas.sample.model;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

/**
 * @author jonatan.salas
 */
@Table
public class Person {
    @Unique
    private Long id;
    private String name;
    private String lastName;
    private String age;
    private String location;

    public Person() { }

    public Person(Long id, String name, String lastName, String age, String location) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Mi nombre es " + getName() + ", me apellido " + getLastName() + ", tengo " + getAge() + " años y vivo en " + getLocation() + ".";
    }
}
