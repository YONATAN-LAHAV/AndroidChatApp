package com.example.whatsapp.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact WHERE ownerId =:owner")
    List<Contact> index(String owner);

    @Query("SELECT * FROM contact WHERE contactId =:contactId")
    Contact get(String contactId);

    @Query("UPDATE contact SET lastMessage =:lastMessage, lastMessageTime =:lastMessageTime WHERE contactId =:contactId")
    void update(String contactId, String lastMessage, String lastMessageTime);

    @Insert
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);
}
