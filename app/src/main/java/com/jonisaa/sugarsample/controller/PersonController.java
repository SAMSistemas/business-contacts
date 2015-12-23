package com.jonisaa.sugarsample.controller;

import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.model.Person;

import java.util.List;

import javax.inject.Singleton;

/**
 * @author jonatan.salas
 */
@Singleton
public class PersonController implements Controller<Person> {
    private static final Class<Person> clazz = Person.class;

    @Override
    public Long insert(Person object) {
        return Person.save(object);
    }

    @Override
    public Person findById(Long id) {
        return Person.findById(clazz, id);
    }

    @Override
    public List<Person> listAll() {
        return Person.listAll(clazz);
    }

    @Override
    public Long update(Person object) {
        return Person.save(object);
    }

    @Override
    public Boolean delete(Person object) {
        return Person.delete(object);
    }

    @Override
    public int deleteAll() {
        return Person.deleteAll(clazz);
    }

    @Override
    public long getCount() {
        return Person.count(clazz);
    }
}
