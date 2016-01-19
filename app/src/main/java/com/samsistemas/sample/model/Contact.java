package com.samsistemas.sample.model;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

/**
 * Class that represents the Contact table
 *
 * @author jonatan.salas
 */
@Table
public class Contact {

    @Unique
    private Long id;

    private String name;

    private String lastName;

    private String age;

    private String location;

    /**
     * Public constructor
     */
    public Contact() { }

    /**
     * Public constructor with params
     *
     * @param id the id of the person to save
     * @param name the name of the person to save
     * @param lastName the lastName of the person to save
     * @param age the age of the person to save
     * @param location the location of the person to save
     */
    public Contact(Long id, String name, String lastName, String age, String location) {
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
        return  "Mi nombre es "
                + getName() + ", me apellido "
                + getLastName() + ", tengo "
                + getAge() + " años y vivo en "
                + getLocation() + ".";
    }
}
