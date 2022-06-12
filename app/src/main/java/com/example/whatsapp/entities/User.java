package com.example.whatsapp.entities;

import androidx.annotation.NonNull;

public class User {
    private String id;
    private String password;
    private String nickname;
    private String conversations;

    public User(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.conversations = null;
    }

    public String getConversations() {
        return conversations;
    }

    public void setConversations(String conversations) {
        this.conversations = conversations;
    }

    public User(String id, String password, String nickname, String conversation) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.conversations = conversation;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }


    public void setId(String id) {
        id = id;
    }

    public void setPassword(String password) {
        password = password;
    }

    public void setNickname(String nickname) {
        nickname = nickname;
    }

}

