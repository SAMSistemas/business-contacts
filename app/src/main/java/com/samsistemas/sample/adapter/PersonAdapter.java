package com.samsistemas.sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsistemas.sample.R;
import com.samsistemas.sample.adapter.base.BaseListAdapter;
import com.samsistemas.sample.model.Person;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter class that presents a Person object data
 *
 * @author jonatan.salas
 */
public class PersonAdapter extends BaseListAdapter {

    /**
     * Default constructor with params.
     *
     * @param context the context used to inflate the item layout resource
     * @param items the list of items to populate by this adapter
     */
    public PersonAdapter(@NonNull Context context, @Nullable  List<Person> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = (Person) getItem(position);
        PersonViewHolder viewHolder;

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            viewHolder = new PersonViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PersonViewHolder) convertView.getTag();
        }

        viewHolder.bindViews(person);

        return convertView;
    }

    /**
     *
     * @author jonatan.salas
     */
    static class PersonViewHolder extends BaseListAdapter.ViewHolder<Person> {
        @Bind(R.id.list_item)
        TextView mListItem;

        public PersonViewHolder(@NonNull View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindViews(Person object) {
            mListItem.setText(object.toString());
        }
    }
}
