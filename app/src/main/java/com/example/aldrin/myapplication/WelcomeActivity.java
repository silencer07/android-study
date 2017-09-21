package com.example.aldrin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {

    @ViewById(R.id.welcomeText)
    TextView welcomeText;


    @Override
    public void onStart(){
        super.onStart();
        Intent i = getIntent();
        welcomeText.setText(welcomeText.getText() + " " + i.getStringExtra("username"));
    }
}
