package com.samsistemas.sample.activity.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Activity class to use as base for all Activities.
 *
 * @author jonatan.salas
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        initialize();
        setupUserInterface();
        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateViews();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        populateViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteListeners();
        delete();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            populateViews();
        }
    }

    /**
     * Method that returns the Layout Resource id used to
     * set the ContentView for the Activity.
     *
     * @return an int representing the layout resource id.
     */
    @LayoutRes
    public abstract int getLayoutResource();

    /**
     * Method that executes during the onCreate, and it used to
     * set up and customize every UI widget corresponding to the
     * Activity.
     */
    public abstract void setupUserInterface();

    /**
     * Method that executes during the onCreate, and it's used to
     * set up the listeners that need some UI widgets to perform some operation.
     */
    public abstract void setListeners();

    /**
     * Method that executes during the onCreate and it's used to
     * initialize some basic data needed for the Activity.
     */
    public abstract void initialize();

    /**
     * Method that executes during all lifecycle method, except from onDestroy.
     * It's main ability is to populate the views with the data that the user needs
     * in the Activity. Here you perform some call to LoaderManager, Consume any API on
     * a background thread, or just to delegate the view population to some controller or
     * presenter object.
     */
    public abstract void populateViews();

    /**
     * Method used to set all the listeners to null. This method executes during the onDestroy
     * callback.
     */
    public abstract void deleteListeners();

    /**
     * Method used to set any global scope var to null. This method runs during the onDestroy
     * callback.
     */
    public abstract void delete();
}
