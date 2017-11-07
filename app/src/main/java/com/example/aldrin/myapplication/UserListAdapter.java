package com.example.aldrin.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import model.User;

/**
 * Created by aldrin on 11/7/17.
 */

public class UserListAdapter extends RealmBaseAdapter<User> implements ListAdapter {

    private static class ViewHolder {
        TextView usernameText;
        TextView passwordText;
    }

    UserListAdapter(OrderedRealmCollection<User> users) {
        super(users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.usernameText = (TextView) convertView.findViewById(R.id.usernameText);
            viewHolder.passwordText = (TextView) convertView.findViewById(R.id.passwordText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            final User user = adapterData.get(position);
            viewHolder.usernameText.setText(user.getUsername());
            viewHolder.passwordText.setText(user.getPassword());

            /*
            if (inDeletionMode) {
                viewHolder.deleteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        countersToDelete.add(item.getCount());
                    }
                });
            } else {
                viewHolder.deleteCheckBox.setOnCheckedChangeListener(null);
            }
            viewHolder.deleteCheckBox.setChecked(countersToDelete.contains(item.getCount()));
            viewHolder.deleteCheckBox.setVisibility(inDeletionMode ? View.VISIBLE : View.GONE);
            */
        }
        return convertView;
    }
}
