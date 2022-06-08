package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.whatsapp.adapters.ContactsListAdapter;
import com.example.whatsapp.entities.ApiContact;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        RecyclerView lstApiContacts = findViewById(R.id.lstApiContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstApiContacts.setAdapter(adapter);
        lstApiContacts.setLayoutManager(new LinearLayoutManager(this));

        List<ApiContact> contacts = new ArrayList<>();
        contacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
        contacts.add(new ApiContact("neta", "neta", "localhost", "Heeeeeeeeeee", "10:00"));
        contacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Hellooo", "10:00"));
        contacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
        contacts.add(new ApiContact("neta", "neta", "localhost", "Heeeeeeeeeee", "10:00"));
        contacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Hellooo", "10:00"));
        contacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
        contacts.add(new ApiContact("neta", "neta", "localhost", "Heeeeeeeeeee", "10:00"));
        contacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Hellooo", "10:00"));

        adapter.setContacts(contacts);
    }
}