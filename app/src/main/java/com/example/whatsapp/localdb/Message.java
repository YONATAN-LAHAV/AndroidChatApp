package com.example.whatsapp.localdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate =  true)
    private int messageId;

    private String created; // time string
    private String contactId;
    private String content;
    private String createdByUser;
    private boolean sent;

    public Message(int messageId, String content, String created, boolean sent, String contactId, String createdByUser) {
        this.messageId = messageId;
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.contactId = contactId;
        this.createdByUser = createdByUser;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }
}




