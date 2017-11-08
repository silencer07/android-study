package com.example.aldrin.myapplication;

import android.support.v7.app.AppCompatActivity;
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

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
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
        final String n = nameText.getText().toString();
        final String e = emailText.getText().toString();
        final String p = passwordText.getText().toString();

        if(StringUtils.isBlank(n) || StringUtils.isBlank(e) || StringUtils.isBlank(p)){
            Toast.makeText(this, "ALL FIELDS NEEDED", Toast.LENGTH_SHORT);
        } else {
            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, birthdayPicker.getYear());
            c.set(Calendar.MONTH, birthdayPicker.getMonth());
            c.set(Calendar.DAY_OF_MONTH, birthdayPicker.getDayOfMonth());

            Realm realm = Realm.getDefaultInstance();
            long count = realm.where(User.class).contains("username", n, Case.INSENSITIVE).count();

            if(count == 0) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        User user = realm.createObject(User.class, n);
                        user.setBirthdate(c.getTime());
                        user.setPassword(p);
                        user.setEmail(e);
                    }
                });
                Toast.makeText(this, "User data saved!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "There is a user with that username already", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Click(R.id.cancelBtn)
    public void onCancelClick(){
        finish();
    }

    private User getUser(){
        Realm realm  = Realm.getDefaultInstance();
        RealmResults<User> results = realm.where(User.class).findAll();
        return results.size() > 0 ? results.get(0) : new User();
    }
}
