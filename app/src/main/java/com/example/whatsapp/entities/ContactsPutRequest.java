package com.example.whatsapp.entities;

public class ContactsPutRequest {
    private String name;
    private String server;

    public ContactsPutRequest(String name, String server) {
        this.name = name;
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
