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

    public Person(String name, String lastName, String age, String location) {
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
}
