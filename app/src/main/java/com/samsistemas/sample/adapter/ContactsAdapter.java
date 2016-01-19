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
import com.samsistemas.sample.model.Contact;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter class that presents a Contact object data
 *
 * @author jonatan.salas
 */
public class ContactsAdapter extends BaseListAdapter {

    /**
     * Default constructor with params.
     *
     * @param context the context used to inflate the item layout resource
     * @param items the list of items to populate by this adapter
     */
    public ContactsAdapter(@NonNull Context context, @Nullable  List<Contact> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Contact contact = (Contact) getItem(position);
        PersonViewHolder viewHolder;

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            viewHolder = new PersonViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PersonViewHolder) convertView.getTag();
        }

        viewHolder.bindViews(contact);

        return convertView;
    }

    /**
     * Contact ViewHolder class
     *
     * @author jonatan.salas
     */
    static class PersonViewHolder extends BaseListAdapter.ViewHolder<Contact> {
        @Bind(R.id.list_item)
        TextView mListItem;

        /**
         * ViewHolder constructor with params
         *
         * @param view the view to bind
         */
        public PersonViewHolder(@NonNull View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindViews(Contact object) {
            mListItem.setText(object.toString());
        }
    }
}
