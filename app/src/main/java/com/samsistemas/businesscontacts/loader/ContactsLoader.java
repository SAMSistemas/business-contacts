package com.samsistemas.businesscontacts.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.businesscontacts.component.DaggerControllerComponent;
import com.samsistemas.businesscontacts.controller.base.Controller;
import com.samsistemas.businesscontacts.model.Contact;

import java.util.List;

/**
 * Loader of Persons data
 *
 * @author jonatan.salas
 */
public class ContactsLoader extends AsyncTaskLoader<List<Contact>> {
    private static final String LOG_TAG = ContactsLoader.class.getSimpleName();
    private Controller<Contact> mPersonController;
    private final Object mLock = new Object();

    /**
     * Constructor with params
     *
     * @param context the application context used to call the super constructor
     */
    public ContactsLoader(Context context) {
        super(context);
        this.mPersonController = DaggerControllerComponent.create().provideContactController();
    }

    @Override
    public List<Contact> loadInBackground() {
        List<Contact> contactList = null;

        if (mPersonController.getCount(Contact.class) == 0) {
            try {
                synchronized (mLock) {
                    mLock.wait(3000);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (mLock) {
                contactList = mPersonController.listAll(Contact.class);
                mLock.notify();
            }
        }

        return (null != contactList) ? contactList : null;
    }
}
