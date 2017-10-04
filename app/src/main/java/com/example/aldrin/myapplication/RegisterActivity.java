package com.example.aldrin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;

import model.User;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    private Gson gson = new Gson();

    @ViewById(R.id.nameText)
    EditText nameText;

    @ViewById(R.id.emailText)
    EditText emailText;

    @ViewById(R.id.passwordText)
    EditText passwordText;

    @ViewById(R.id.birthday)
    DatePicker birthdayPicker;

    @AfterViews
    public void initialize(){
        Calendar c = Calendar.getInstance();
        birthdayPicker.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    @Click(R.id.okBtn)
    public void onOkClick(View v){
        String n = nameText.getText().toString();
        String e = emailText.getText().toString();
        String p = passwordText.getText().toString();

        if(StringUtils.isBlank(n) || StringUtils.isBlank(e) || StringUtils.isBlank(p)){
            Toast.makeText(this, "ALL FIELDS NEEDED", Toast.LENGTH_SHORT);
        } else {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, birthdayPicker.getYear());
            c.set(Calendar.MONTH, birthdayPicker.getMonth());
            c.set(Calendar.DAY_OF_MONTH, birthdayPicker.getDayOfMonth());

            User user = getUser();
            user.setBirthdate(c.getTime());
            user.setUsername(n);
            user.setPassword(p);
            user.setEmail(e);

            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putString(User.KEY, gson.toJson(user));
            Toast.makeText(this, "User data saved!", Toast.LENGTH_SHORT).show();
            editor.commit();
            finish();
        }
    }

    @Click(R.id.cancelBtn)
    public void onCancelClick(){
        finish();
    }

    private User getUser(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String userJSON = prefs.getString(User.KEY, "");
        return StringUtils.isNotBlank(userJSON) ? gson.fromJson(userJSON, User.class) : new User();
    }
}
