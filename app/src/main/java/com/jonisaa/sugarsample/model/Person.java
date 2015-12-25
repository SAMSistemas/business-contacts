package com.jonisaa.sugarsample.model;

import com.orm.SugarRecord;

/**
 * @author jonatan.salas
 */
public class Person extends SugarRecord {
    private String name;
    private String lastName;
    private String age;
    private String location;

    public Person() { }

    public Person(Long id, String name, String lastName, String age, String location) {
        this.setId(id);
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
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
