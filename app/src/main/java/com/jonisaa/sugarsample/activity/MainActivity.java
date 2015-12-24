package com.jonisaa.sugarsample.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jonisaa.sugarsample.adapter.PersonAdapter;
import com.jonisaa.sugarsample.R;
import com.jonisaa.sugarsample.activity.base.BaseAppCompatActivity;
import com.jonisaa.sugarsample.component.ControllerComponent;
import com.jonisaa.sugarsample.component.DaggerControllerComponent;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.loader.PersonsLoader;
import com.jonisaa.sugarsample.model.Person;

import java.util.List;

import butterknife.Bind;

/**
 * @author jonatan.salas
 */
public class MainActivity extends BaseAppCompatActivity {
    private static final int PERSONS_LOADER_ID = 0;

    @Bind(R.id.listView)
    ListView mListView;

    private PersonAdapter mPersonAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void setupUserInterface() {
        mListView.setDividerHeight(1);
        mListView.setEnabled(true);
        mListView.setClickable(true);
    }

    @Override
    public void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Person person = (Person) parent.getAdapter().getItem(position);
                Snackbar.make(view, person.toString(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initialize() {
        new LoadPersonsInBackground().execute();
    }

    @Override
    public void populateViews() {
        getSupportLoaderManager().initLoader(PERSONS_LOADER_ID, null, new LoaderManager.LoaderCallbacks<List<Person>>() {

            @Override
            public Loader<List<Person>> onCreateLoader(int id, Bundle args) {
                return new PersonsLoader(getApplicationContext());
            }

            @Override
            public void onLoadFinished(Loader<List<Person>> loader, List<Person> data) {
                if (null == data || data.isEmpty()) {
                    loader.reset();
                } else {
                    mPersonAdapter = new PersonAdapter(getApplicationContext(), data);
                    mListView.setAdapter(mPersonAdapter);
                    mPersonAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLoaderReset(Loader<List<Person>> loader) {
                if (!loader.isReset()) {
                    loader.reset();
                }
            }
        }).forceLoad();
    }

    @Override
    public void deleteListeners() { }

    public class LoadPersonsInBackground extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            final ControllerComponent component = DaggerControllerComponent.create();
            final Controller<Person> controller = component.providePersonController();

            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            controller.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Snackbar.make(mListView, "Insertados exitosamente!", Toast.LENGTH_SHORT).show();
        }
    }
}
