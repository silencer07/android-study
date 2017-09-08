package com.example.aldrin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private CheckBox rememberMeCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        rememberMeCheckbox = (CheckBox) findViewById(R.id.remeberMe);
    }

    public void login(View v){
        String message = String.format("username: %s \n password: %s \n rememberMe: %s",
                usernameText.getText(),
                passwordText.getText(),
                rememberMeCheckbox.isChecked()
        );
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
