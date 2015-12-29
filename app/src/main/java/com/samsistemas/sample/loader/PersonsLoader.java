package com.samsistemas.sample.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.sample.component.DaggerControllerComponent;
import com.samsistemas.sample.controller.base.Controller;
import com.samsistemas.sample.model.Person;

import java.util.List;

/**
 * Loader of Persons data
 *
 * @author jonatan.salas
 */
public class PersonsLoader extends AsyncTaskLoader<List<Person>> {
    private static final String LOG_TAG = PersonsLoader.class.getSimpleName();
    private Controller<Person> mPersonController;
    private final Object mLock = new Object();

    /**
     * Constructor with params
     *
     * @param context the application context used to call the super constructor
     */
    public PersonsLoader(Context context) {
        super(context);
        this.mPersonController = DaggerControllerComponent.create().providePersonController();
    }

    @Override
    public List<Person> loadInBackground() {
        List<Person> personList = null;

        if (mPersonController.getCount(Person.class) == 0) {
            try {
                synchronized (mLock) {
                    mLock.wait(3000);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (mLock) {
                personList = mPersonController.listAll(Person.class);
                mLock.notify();
            }
        }

        return (null != personList) ? personList : loadInBackground();
    }
}
