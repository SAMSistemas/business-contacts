package com.jonisaa.sugarsample.activity;

import android.os.Bundle;
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
import com.jonisaa.sugarsample.loader.PersonsLoader;
import com.jonisaa.sugarsample.model.Person;
import com.jonisaa.sugarsample.service.FetchPersonsTask;
import com.jonisaa.sugarsample.utility.DeveloperUtility;

import java.util.List;

import butterknife.Bind;

import static com.jonisaa.sugarsample.constant.ApplicationConstant.PERSONS_LOADER_ID;

/**
 * @author jonatan.salas
 */
public class MainActivity extends BaseAppCompatActivity {

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
        new FetchPersonsTask(mListView).execute();
        DeveloperUtility.enableStrictModeApi(true);
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
                return (id == PERSONS_LOADER_ID) ? new PersonsLoader(getApplicationContext()) : null;
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
}
