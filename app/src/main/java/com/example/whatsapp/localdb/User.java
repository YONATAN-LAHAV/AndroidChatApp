package com.example.whatsapp.localdb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate =  false)
    @NonNull
    private String userId;
    private String password;
    private String picture;

    public User(String userId, String password, String picture) {
        this.userId = userId;
        this.password = password;
        this.picture = picture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
