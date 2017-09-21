package com.example.aldrin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @ViewById(R.id.nameText)
    EditText nameText;

    @ViewById(R.id.emailText)
    EditText emailText;

    @ViewById(R.id.passwordText)
    EditText passwordText;

    @Click(R.id.okBtn)
    public void onOkClick(View v){
        String n = nameText.getText().toString();
        String e = emailText.getText().toString();
        String p = passwordText.getText().toString();

        if(StringUtils.isBlank(n) || StringUtils.isBlank(e) || StringUtils.isBlank(p)){
            Toast.makeText(this, "ALL FIELDS NEEDED", Toast.LENGTH_SHORT);
        } else {
            Intent i = new Intent();
            i.putExtra("username", e);
            i.putExtra("password", p);
            setResult(Activity.RESULT_OK, i);
            finish();
        }
    }

    @Click(R.id.cancelBtn)
    public void onCancelClick(){
        setResult(Activity.RESULT_CANCELED, new Intent());
        finish();
    }
}
