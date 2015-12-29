package com.jonisaa.sugarsample.service;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.jonisaa.sugarsample.component.DaggerControllerComponent;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.model.Person;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static com.jonisaa.sugarsample.constant.ApplicationConstant.BASE_URL;

/**
 * @author jonatan.salas
 */
public class FetchPersonsTask extends AsyncTask<Void, Void, Boolean> implements Callback<List<Person>> {

    @Nullable
    private View mAttachedView;

    @NonNull
    private Controller<Person> mPersonController;

    public FetchPersonsTask(@Nullable View view) {
        this.mAttachedView = view;
        this.mPersonController = DaggerControllerComponent.create().providePersonController();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PersonService personService = retrofit.create(PersonService.class);
        final Call<List<Person>> queue = personService.listPersons();
        queue.enqueue(this);

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (null != mAttachedView) {
            Snackbar.make(mAttachedView, "Insertados exitosamente!", Snackbar.LENGTH_SHORT).show();
        }
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
