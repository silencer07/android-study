package com.example.aldrin.myapplication;

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

@EActivity(R.layout.login_layout)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.username)
    EditText usernameText;

    @ViewById(R.id.password)
    EditText passwordText;

    @ViewById(R.id.remeberMe)
    CheckBox rememberMeCheckbox;

    @Click(R.id.signInButton)
    public void login(View v){
        String message = String.format("username: %s \n password: %s \n rememberMe: %s",
                usernameText.getText(),
                passwordText.getText(),
                rememberMeCheckbox.isChecked()
        );
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
