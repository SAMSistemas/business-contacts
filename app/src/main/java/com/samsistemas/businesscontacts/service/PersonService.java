package com.samsistemas.businesscontacts.service;

import com.samsistemas.businesscontacts.model.Contact;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Interface that represents the Service that interacts with the REST API
 *
 * @author jonatan.salas
 */
public interface PersonService {

    /**
     * Method that list all people available in the web service
     *
     * @return a Call to the list of Persons
     */
    @GET("/people")
    Call<List<Contact>> listPersons();
}
