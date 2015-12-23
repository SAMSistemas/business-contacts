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
    protected static final String LOG_TAG = BaseAppCompatActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        initialize();
        setupUserInterface();
        populateViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUserInterface();
        populateViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteListeners();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setupUserInterface();
        populateViews();
    }

    @LayoutRes
    public abstract int getLayoutResource();

    public abstract void setupUserInterface();

    public abstract void setListeners();

    public abstract void initialize();

    public abstract void populateViews();

    public abstract void deleteListeners();
}
