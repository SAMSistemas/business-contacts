package com.samsistemas.sample.service;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.samsistemas.sample.component.DaggerControllerComponent;
import com.samsistemas.sample.controller.base.Controller;
import com.samsistemas.sample.model.Contact;

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
public class FetchPersonsTask extends AsyncTask<Void, Void, Void> implements Callback<List<Contact>> {

    @NonNull
    private View mView;

    @NonNull
    private Controller<Contact> mPersonController;

    /**
     * Default Constructor
     */
    public FetchPersonsTask(@NonNull View view) {
        this.mView = view;
        this.mPersonController = DaggerControllerComponent.create().provideContactController();
    }

    @Override
    protected Void doInBackground(Void... params) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PersonService personService = retrofit.create(PersonService.class);
        final Call<List<Contact>> queue = personService.listPersons();
        queue.enqueue(this);

        return null;
    }

    @Override
    public void onResponse(Response<List<Contact>> response, Retrofit retrofit) {
        final List<Contact> contactList = response.body();

        if (null != contactList) {
            for (int i = 0; i < contactList.size(); i++) {
                mPersonController.insert(contactList.get(i));
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        cancel(true);
        Snackbar.make(mView, "There is a problem when retrieving the data", Snackbar.LENGTH_SHORT).show();
    }
}
