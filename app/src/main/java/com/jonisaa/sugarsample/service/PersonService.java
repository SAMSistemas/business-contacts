package com.jonisaa.sugarsample.service;

import com.jonisaa.sugarsample.model.Person;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author jonatan.salas
 */
public interface PersonService {

    @GET("/people")
    Call<List<Person>> listPersons();
}
