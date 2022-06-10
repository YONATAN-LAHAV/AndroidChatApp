package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.whatsapp.adapters.ApiContactListAdapter;
import com.example.whatsapp.viewmodels.ApiContactViewModel;

public class ContactsActivity extends AppCompatActivity {

    private ApiContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Init viewModel field.
        viewModel = new ViewModelProvider(this).get(ApiContactViewModel.class);

        // RecycleView logic.
        RecyclerView lstApiContacts = findViewById(R.id.lstApiContacts);
        final ApiContactListAdapter adapter = new ApiContactListAdapter(this);
        lstApiContacts.setAdapter(adapter);
        lstApiContacts.setLayoutManager(new LinearLayoutManager(this));

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
}