package com.samsistemas.sample.service;

import com.samsistemas.sample.model.Person;

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
