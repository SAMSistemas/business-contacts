package com.samsistemas.businesscontacts.adapter.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class BaseListAdapter extends BaseAdapter {

    @NonNull
    protected Context mContext;

    @Nullable
    protected List mItems;

    /**
     * BaseListAdapter constructor with params
     *
     * @param context the context used to inflate the view that represent an Item in the ListView
     * @param items the list of items to populate
     */
    public BaseListAdapter(@NonNull Context context, @Nullable List items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return (null != mItems) ? mItems.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (null != mItems) ? mItems.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (null != mItems) ? position : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /**
     * ViewHolder abstract class
     *
     * @author jonatan.salas
     * @param <T> the object to bind with the view
     */
    public abstract static class ViewHolder<T> {

        /**
         * Abstract method whose purpose is to bind the view with the model
         *
         * @param object the object containing the data to be presented in a ListView item
         */
        public abstract void bindViews(T object);
    }
}
