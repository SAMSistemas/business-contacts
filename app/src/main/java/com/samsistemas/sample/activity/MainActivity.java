package com.samsistemas.sample.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.samsistemas.sample.adapter.ContactsAdapter;
import com.samsistemas.sample.R;
import com.samsistemas.sample.loader.ContactsLoader;
import com.samsistemas.sample.loader.SearchContactsLoader;
import com.samsistemas.sample.model.Contact;
import com.samsistemas.sample.service.FetchPersonsTask;
import com.samsistemas.sample.utility.DeveloperUtility;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.samsistemas.sample.constant.ApplicationConstant.CONTACTS_LOADER_ID;
import static com.samsistemas.sample.constant.ApplicationConstant.CONTACTS_QUERY_LOADER_ID;

/**
 * Main activity controller
 *
 * @author jonatan.salas
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView mListView;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Bind(R.id.textView)
    TextView mTextView;

    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mListView.setDividerHeight(1);
        mListView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminate(true);

        new FetchPersonsTask(mListView).execute();
        DeveloperUtility.enableStrictModeApi(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initContactsLoader();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initContactsLoader();
    }

    @Override
    protected void onDestroy() {
        mListView.setAdapter(null);
        mContactsAdapter = null;
        mProgressBar = null;
        mListView = null;

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    initContactsQueryLoader(newText);
                } else {
                    restartContactsLoader();
                }

                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                restartContactsLoader();
                return true;
            }
        });

        return true;
    }

    private void initContactsLoader() {
        getSupportLoaderManager().initLoader(CONTACTS_LOADER_ID, null,
                new LoaderManager.LoaderCallbacks<List<Contact>>() {
                    @Override
                    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
                        return (id == CONTACTS_LOADER_ID) ? new ContactsLoader(getApplicationContext()) : null;
                    }

                    @Override
                    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> data) {
                        if (null != data && !data.isEmpty()) {
                            mContactsAdapter = new ContactsAdapter(getApplicationContext(), data);
                            mProgressBar.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            mListView.setAdapter(mContactsAdapter);
                            mContactsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<List<Contact>> loader) {
                        if (!loader.isReset()) {
                            loader.reset();
                        }
                    }
                }
        ).forceLoad();
    }

    private void restartContactsLoader() {
        getSupportLoaderManager().restartLoader(CONTACTS_LOADER_ID, null,
                new LoaderManager.LoaderCallbacks<List<Contact>>() {

                    @Override
                    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
                        return (id == CONTACTS_LOADER_ID) ? new ContactsLoader(getApplicationContext()) : null;
                    }

                    @Override
                    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> data) {
                        if (null != data && !data.isEmpty()) {
                            mProgressBar.setVisibility(View.GONE);
                            mContactsAdapter = new ContactsAdapter(getApplicationContext(), data);
                            mListView.setVisibility(View.VISIBLE);
                            mListView.setAdapter(mContactsAdapter);
                            mContactsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<List<Contact>> loader) {
                        if (!loader.isReset()) {
                            loader.reset();
                        }
                    }
                }
        ).forceLoad();
    }

    private void initContactsQueryLoader(final String query) {
        getSupportLoaderManager().restartLoader(CONTACTS_QUERY_LOADER_ID, null,
                new LoaderManager.LoaderCallbacks<List<Contact>>() {
                    @Override
                    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
                        return (id == CONTACTS_QUERY_LOADER_ID) ? new SearchContactsLoader(getApplicationContext(), query) : null;
                    }

                    @Override
                    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> data) {
                        if (null == data || data.isEmpty()) {
                            mProgressBar.setVisibility(View.GONE);
                            mTextView.setText("There aren't contacts. Try to synchronize again");
                            mListView.setEmptyView(mTextView);
                        } else {
                            mTextView.setVisibility(View.GONE);
                            mContactsAdapter = new ContactsAdapter(getApplicationContext(), data);
                            mListView.setVisibility(View.VISIBLE);
                            mListView.setAdapter(mContactsAdapter);
                            mContactsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<List<Contact>> loader) {
                        if (!loader.isReset()) {
                            loader.reset();
                        }
                    }
                }
        ).forceLoad();
    }
}
