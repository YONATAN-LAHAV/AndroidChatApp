package com.example.whatsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Transaction
    @Query("SELECT * FROM user")
//    List<UserWithConversation> index();
       List<UserWithConversation> index();


    @Query("SELECT * FROM user WHERE id = :id")
    User get(String id);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);
}

