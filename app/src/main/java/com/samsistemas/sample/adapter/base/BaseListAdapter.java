package com.samsistemas.sample.adapter.base;

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

    public static abstract class ViewHolder<T> {

        public abstract void bindViews(T object);
    }
}
