package com.example.aldrin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import io.realm.Realm;
import io.realm.RealmResults;
import model.User;

@EActivity(R.layout.login_layout)
public class MainActivity extends AppCompatActivity {

    public static final String REMEMBER_ME = "REMEMBER_ME";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String LOGGED_IN_USER = "loggedInUser";

    @ViewById(R.id.username)
    EditText usernameText;

    @ViewById(R.id.password)
    EditText passwordText;

    @ViewById(R.id.remeberMe)
    CheckBox rememberMeCheckbox;

    private Gson gson = new Gson();

    @Click(R.id.signInButton)
    public void login(View v){
        String u = usernameText.getText().toString();
        String p = passwordText.getText().toString();
        if(StringUtils.isEmpty(u) && StringUtils.isEmpty(p)){
            Toast.makeText(this, "No user", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = getUser(u, p);
        if(user != null && user.isCredentialsCorrect(u,p)){
            onRememberMeChange();
            Intent i = new Intent(this, WelcomeActivity_.class);
            i.putExtra(LOGGED_IN_USER, u);
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private User getUser(String username, String password){
        Realm realm  = Realm.getDefaultInstance();
        return realm.where(User.class).equalTo("username", username).equalTo("password", password).findFirst();
    }

    private boolean hasUsers(){
        Realm realm  = Realm.getDefaultInstance();
        return realm.where(User.class).count() > 0;
    }

    @AfterViews
    public void showRegisterPageIfNoRegisteredUser(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if(prefs.getBoolean(REMEMBER_ME, false)){
            usernameText.setText(prefs.getString(USERNAME, ""));
            passwordText.setText(prefs.getString(PASSWORD, ""));
        }

        if(!hasUsers()){
            startActivity(new Intent(this, RegisterActivity_.class));
        } else {
            rememberMeCheckbox.setChecked(prefs.getBoolean(REMEMBER_ME, false));
        }
    }

    @Click(R.id.userListButton)
    public void showUserList(){
        startActivity(new Intent(this, UserListActivity_.class));
    }

    @CheckedChange(R.id.remeberMe)
    public void onRememberMeChange(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean(REMEMBER_ME, rememberMeCheckbox.isChecked());
        editor.putString(USERNAME, usernameText.getText().toString());
        editor.putString(PASSWORD, passwordText.getText().toString());
//        Toast.makeText(this, String.valueOf(rememberMeCheckbox.isChecked()), Toast.LENGTH_SHORT).show();
        editor.commit();
    }
}
