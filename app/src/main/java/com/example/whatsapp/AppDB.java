package com.example.whatsapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsapp.entities.Conversation;
import com.example.whatsapp.entities.User;

@Database(entities = {User.class, Conversation.class}, version = 1)
public  abstract class AppDB extends RoomDatabase{
    public abstract UserDao UserDao();
    public abstract ConversationDao ConversationDao();
}
