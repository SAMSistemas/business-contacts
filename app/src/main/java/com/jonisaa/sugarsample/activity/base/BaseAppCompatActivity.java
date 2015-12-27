package com.jonisaa.sugarsample.activity.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
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

    @LayoutRes
    public abstract int getLayoutResource();

    public abstract void setupUserInterface();

    public abstract void setListeners();

    public abstract void initialize();

    public abstract void populateViews();

    public abstract void deleteListeners();

    public abstract void delete();
}
