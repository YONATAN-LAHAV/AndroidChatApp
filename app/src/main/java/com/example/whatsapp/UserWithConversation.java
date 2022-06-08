package com.example.whatsapp;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class UserWithConversation {
    @Embedded public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "UserId"
    )
    public List<Conversation> conversations;


}
