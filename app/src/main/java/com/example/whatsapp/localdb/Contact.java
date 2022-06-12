package com.example.whatsapp.localdb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate =  false)
    @NonNull
    private String contactId;
    private String ownerId;
    private int pictureId;
    private String lastMessage;
    private String lastMessageTime;

    public Contact(@NonNull String contactId, String ownerId, int pictureId, String lastMessage, String lastMessageTime) {
        this.contactId = contactId;
        this.ownerId = ownerId;
        this.pictureId = pictureId;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

    @NonNull
    public String getContactId() {
        return contactId;
    }

    public void setContactId(@NonNull String contactId) {
        this.contactId = contactId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
