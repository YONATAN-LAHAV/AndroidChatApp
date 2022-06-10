package com.example.whatsapp.entities;


import androidx.room.PrimaryKey;

public class ApiMessage {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    private String _content;
    private String _created;
    private boolean _sent;

    public ApiMessage(String content, String created, boolean sent) {
        this._content = content;
        this._created = created;
        this._sent = sent;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public String get_created() {
        return _created;
    }

    public void set_created(String _created) {
        this._created = _created;
    }

    public boolean is_sent() {
        return _sent;
    }

    public void set_sent(boolean _sent) {
        this._sent = _sent;
    }
}
