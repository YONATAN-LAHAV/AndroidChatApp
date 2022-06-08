package com.example.whatsapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsapp.entities.Conversation;

import java.util.List;

@Dao
public interface ConversationDao {
    @Query("SELECT * FROM conversation")
    List<Conversation> index();

    @Query("SELECT * FROM conversation WHERE id = :id")
    Conversation get(int id);

    @Insert
    void insert(Conversation... conversations);

    @Update
    void update(Conversation... conversations);

    @Delete
    void delete(Conversation... conversations);
}
