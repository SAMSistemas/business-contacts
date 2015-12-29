package com.samsistemas.sample.service;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.samsistemas.sample.component.DaggerControllerComponent;
import com.samsistemas.sample.controller.base.Controller;
import com.samsistemas.sample.model.Person;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static com.samsistemas.sample.constant.ApplicationConstant.BASE_URL;

/**
 * AsyncTask that performs the insert of Persons using SugarORM
 *
 * @author jonatan.salas
 */
public class FetchPersonsTask extends AsyncTask<Void, Void, Void> implements Callback<List<Person>> {

    @NonNull
    private Controller<Person> mPersonController;

    /**
     * Default Constructor
     */
    public FetchPersonsTask() {
        this.mPersonController = DaggerControllerComponent.create().providePersonController();
    }

    @Override
    protected Void doInBackground(Void... params) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PersonService personService = retrofit.create(PersonService.class);
        final Call<List<Person>> queue = personService.listPersons();
        queue.enqueue(this);

        return null;
    }

    @Override
    public void onResponse(Response<List<Person>> response, Retrofit retrofit) {
        final List<Person> personList = response.body();

        if (null != personList) {
            for (int i = 0; i < personList.size(); i++) {
                mPersonController.insert(personList.get(i));
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (null != t && !isCancelled()) {
            cancel(true);
        }
    }
}
