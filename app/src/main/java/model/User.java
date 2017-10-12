package model;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{

    public static final String KEY = "KEY";

    private String username;
    private String password;
    private String email;
    private Date birthdate;

    @PrimaryKey
    private long id;

    public boolean isCredentialsCorrect(String username, String password){
        return StringUtils.equals(this.username, username) && StringUtils.equals(this.password, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
