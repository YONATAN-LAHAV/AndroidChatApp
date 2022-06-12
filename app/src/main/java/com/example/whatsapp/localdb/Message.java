package com.example.whatsapp.localdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate =  true)
    private int messageId;

    private String created;    // time string
    private String receiverContact;
    private String content;
    private String senderUser;
    private boolean sent;

    public Message(int messageId, String content, String created, boolean sent, String receiverContact, String senderUser) {
        this.messageId = messageId;
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.receiverContact = receiverContact;
        this.senderUser = senderUser;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(String receiverContact) {
        this.receiverContact = receiverContact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(String senderUser) {
        this.senderUser = senderUser;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}




