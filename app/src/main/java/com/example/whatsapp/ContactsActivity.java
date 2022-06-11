package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.whatsapp.adapters.ApiContactListAdapter;
import com.example.whatsapp.interfaces.ListItemClickListener;
import com.example.whatsapp.viewmodels.ApiContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactsActivity extends AppCompatActivity implements ListItemClickListener {

    private ApiContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Set button to add new contact screen.
        FloatingActionButton btnToAddContact = findViewById(R.id.btnAddContactScreen);
        btnToAddContact.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        });

        // Init viewModel field.
        viewModel = new ViewModelProvider(this).get(ApiContactViewModel.class);

        // RecycleView logic.
        RecyclerView lstApiContacts = findViewById(R.id.lstApiContacts);
        final ApiContactListAdapter adapter = new ApiContactListAdapter(this, this);
        lstApiContacts.setAdapter(adapter);
        lstApiContacts.setLayoutManager(new LinearLayoutManager(this));

        lstApiContacts.setClickable(true);

        // Set observer on the data in the viewModel. when viewModel data will change,
        // the method will activate.
        viewModel.get().observe(this, apiContacts -> {
            adapter.setContacts(apiContacts);
        });

//        List<ApiContact> contacts = new ArrayList<>();
//        contacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//        contacts.add(new ApiContact("neta", "neta", "localhost", "Heeeeeeeeeee", "10:00"));
//        contacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Hellooo", "10:00"));
//        contacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//        contacts.add(new ApiContact("neta", "neta", "localhost", "Heeeeeeeeeee", "10:00"));
//        contacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Hellooo", "10:00"));
//        contacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//        contacts.add(new ApiContact("neta", "neta", "localhost", "Heeeeeeeeeee", "10:00"));
//        contacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Hellooo", "10:00"));
//        adapter.setContacts(contacts);
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}