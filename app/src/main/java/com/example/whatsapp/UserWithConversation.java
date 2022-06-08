package com.example.whatsapp;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.whatsapp.entities.Conversation;
import com.example.whatsapp.entities.User;

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
