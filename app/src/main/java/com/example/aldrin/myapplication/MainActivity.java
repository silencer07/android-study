package com.example.aldrin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

@EActivity(R.layout.login_layout)
public class MainActivity extends AppCompatActivity {

    private static final String HARDCODED_USERNAME = "aldrin";
    private static final String HARDCODED_PASSWORD = "password";


    @ViewById(R.id.username)
    EditText usernameText;

    @ViewById(R.id.password)
    EditText passwordText;

    @ViewById(R.id.remeberMe)
    CheckBox rememberMeCheckbox;

    @Click(R.id.signInButton)
    public void login(View v){
        String u = usernameText.getText().toString();
        String p = passwordText.getText().toString();
        if(StringUtils.isEmpty(u) && StringUtils.isEmpty(p)){
            Toast.makeText(this, "No user", Toast.LENGTH_SHORT).show();
        } else if(HARDCODED_USERNAME.equals(u) && HARDCODED_PASSWORD.equals(p)){
            Intent i = new Intent(this, WelcomeActivity_.class);
            i.putExtra("username", u);
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.registerButton)
    public void register(View v){
        Intent i = new Intent(this, RegisterActivity_.class);
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK) {
            usernameText.setText(data.getStringExtra("username"));
            passwordText.setText(data.getStringExtra("password"));
        }
    }
}
