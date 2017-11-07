package com.example.aldrin.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import io.realm.RealmResults;
import model.User;

@EActivity(R.layout.activity_user_list)
public class UserListActivity extends AppCompatActivity {

    @ViewById(R.id.userListView)
    ListView listView;

    @AfterViews
    public void showUserList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<User> users = realm.where(User.class).findAllSorted("username");
        ListAdapter adapter = new UserListAdapter(users);
        listView.setAdapter(adapter);
    }

}
