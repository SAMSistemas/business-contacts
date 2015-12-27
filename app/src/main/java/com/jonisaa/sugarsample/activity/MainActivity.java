package com.jonisaa.sugarsample.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.jonisaa.sugarsample.adapter.PersonAdapter;
import com.jonisaa.sugarsample.R;
import com.jonisaa.sugarsample.activity.base.BaseAppCompatActivity;
import com.jonisaa.sugarsample.component.ControllerComponent;
import com.jonisaa.sugarsample.component.DaggerControllerComponent;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.loader.PersonsLoader;
import com.jonisaa.sugarsample.model.Person;
import com.jonisaa.sugarsample.utility.DeveloperUtility;

import java.util.List;

import butterknife.Bind;

/**
 * @author jonatan.salas
 */
public class MainActivity extends BaseAppCompatActivity {
    private static final int PERSONS_LOADER_ID = 0;

    @Bind(R.id.listView)
    ListView mListView;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    private PersonAdapter mPersonAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void setupUserInterface() {
        mListView.setDividerHeight(1);
        mListView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminate(true);
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
        new LoadPersonsInBackground(mListView).execute();
        DeveloperUtility.enableDeveloperStrictMode(true);
    }

    @Override
    public void populateViews() {
        initPersonsLoader();
    }

    @Override
    public void deleteListeners() {
        mListView.setOnItemClickListener(null);
    }

    @Override
    public void delete() {
        mListView.setAdapter(null);
        mPersonAdapter = null;
        mProgressBar = null;
        mListView = null;
    }

    private void initPersonsLoader() {
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
                    mProgressBar.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
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

    public static  class LoadPersonsInBackground extends AsyncTask<Void, Void, Boolean> {
        @Nullable
        private View mAttachedView;

        public LoadPersonsInBackground(@Nullable View v) {
            this.mAttachedView = v;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            final ControllerComponent component = DaggerControllerComponent.create();
            final Controller<Person> controller = component.providePersonController();

            if (controller.getCount() == 0) {
                controller.insert(new Person(1L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(2L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(3L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(4L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(5L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(6L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(7L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(8L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(9L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(10L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(11L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(12L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(13L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(14L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(15L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(16L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(17L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(18L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(19L, "Mariano", "Loisotto", "23", "Florencio Varela"));
                controller.insert(new Person(20L, "Mariano", "Loisotto", "23", "Florencio Varela"));
            } else {
                cancel(true);
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (null != mAttachedView) {
                Snackbar.make(mAttachedView, "Insertados exitosamente!", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
