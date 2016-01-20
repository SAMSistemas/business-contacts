package com.samsistemas.businesscontacts.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.businesscontacts.component.DaggerControllerComponent;
import com.samsistemas.businesscontacts.controller.base.Controller;
import com.samsistemas.businesscontacts.model.Contact;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class SearchContactsLoader extends AsyncTaskLoader<List<Contact>> {
    private static final String LOG_TAG = SearchContactsLoader.class.getSimpleName();
    private String mQuery;
    private Controller<Contact> mContactController;
    private final Object mLock = new Object();

    public SearchContactsLoader(Context context, String query) {
        super(context);
        this.mQuery = query;
        this.mContactController = DaggerControllerComponent.create().provideContactController();
    }

    @Override
    public List<Contact> loadInBackground() {
        List<Contact> contactList = null;

        if (mContactController.getCount(Contact.class) == 0) {
            try {
                synchronized (mLock) {
                    mLock.wait(3000);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (mLock) {
                contactList = mContactController.search(Contact.class, mQuery);
            }
        }

        return (null != contactList) ? contactList : null;
    }
}
