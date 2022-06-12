package com.example.whatsapp.localdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Message.class, User.class}, version = 5)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract UserDao userDao();
    public abstract MessageDao messageDao();

}
