package com.jonisaa.sugarsample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jonisaa.sugarsample.R;
import com.jonisaa.sugarsample.model.Person;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class PersonAdapter extends BaseAdapter {

    @Nullable
    private List<Person> mItems;

    @NonNull
    private Context mContext;

    public PersonAdapter(@NonNull Context context, @Nullable  List<Person> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return (null != mItems && !mItems.isEmpty()) ? mItems.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (null != mItems && !mItems.isEmpty()) ? mItems.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (null != mItems && !mItems.isEmpty()) ? mItems.get(position).getId() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = (Person) getItem(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        final TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(person.toString());

        return convertView;
    }
}
