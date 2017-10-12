package com.example.aldrin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import model.User;

@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {

    Gson gson = new Gson();

    @ViewById(R.id.welcomeText)
    TextView welcomeText;


    @AfterViews
    public void initialize(){
        if(StringUtils.isBlank(welcomeText.getText())) {
            User user = getUser();
            StringBuilder welcome = new StringBuilder("Welcome ")
                    .append(user.getUsername());
            SimpleDateFormat fmt = new SimpleDateFormat("MMdd", Locale.US);
            if (fmt.format(new Date()).equals(fmt.format(user.getBirthdate()))) {
                welcome.append(" and happy birthday");
            }
            welcomeText.setText(welcome.toString());
        }
    }

    private User getUser(){
        Realm realm  = Realm.getDefaultInstance();
        return realm.where(User.class).findFirst();
    }
}
