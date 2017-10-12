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
        User user = getUser();
        if(StringUtils.isEmpty(u) && StringUtils.isEmpty(p)){
            Toast.makeText(this, "No user", Toast.LENGTH_SHORT).show();
        } else if(user != null && user.isCredentialsCorrect(u,p)){
            Intent i = new Intent(this, WelcomeActivity_.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private User getUser(){
        Realm realm  = Realm.getDefaultInstance();
        return realm.where(User.class).findFirst();
    }

    @AfterViews
    public void showRegisterPageIfNoRegisteredUser(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        User user = getUser();
        if(prefs.getBoolean(REMEMBER_ME, false)){
            if(user != null){
                usernameText.setText(user.getUsername());
                passwordText.setText(user.getPassword());
            } else {
                Toast.makeText(this, "Nothing to remember",Toast.LENGTH_SHORT).show();
            }
        } else if(user == null){
            register();
        }
        rememberMeCheckbox.setChecked(prefs.getBoolean(REMEMBER_ME, false));
    }

    @Click(R.id.registerButton)
    public void register(){
        Intent i = new Intent(this, RegisterActivity_.class);
        startActivity(new Intent(this, RegisterActivity_.class));
    }

    @CheckedChange(R.id.remeberMe)
    public void onRememberMeChange(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean(REMEMBER_ME, rememberMeCheckbox.isChecked());
//        Toast.makeText(this, String.valueOf(rememberMeCheckbox.isChecked()), Toast.LENGTH_SHORT).show();
        editor.commit();
    }
}
