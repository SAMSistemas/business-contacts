package com.jonisaa.sugarsample.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.jonisaa.sugarsample.component.DaggerControllerComponent;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.model.Person;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class PersonsLoader extends AsyncTaskLoader<List<Person>> {
    private Controller<Person> mPersonController;

    public PersonsLoader(Context context) {
        super(context);
        this.mPersonController = DaggerControllerComponent.create().providePersonController();
    }

    @Override
    public List<Person> loadInBackground() {
        List<Person> persons = mPersonController.listAll();
        return (null != persons) ? persons : loadInBackground();
    }
}
