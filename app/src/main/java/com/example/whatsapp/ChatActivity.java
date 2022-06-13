package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.whatsapp.adapters.ApiContactListAdapter;
import com.example.whatsapp.adapters.ApiMessageListAdapter;
import com.example.whatsapp.entities.LoginPostRequest;
import com.example.whatsapp.interfaces.ListItemClickListener;
import com.example.whatsapp.viewmodels.ApiContactViewModel;
import com.example.whatsapp.viewmodels.ApiMessageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatActivity extends AppCompatActivity {

    private ApiMessageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // Init viewModel field.
        viewModel = new ViewModelProvider(this).get(ApiMessageViewModel.class);

        // RecycleView logic.
        RecyclerView lstApiMessages = findViewById(R.id.lstApiMessages);
        final ApiMessageListAdapter adapter = new ApiMessageListAdapter(this, getIntent().getExtras().get("ContactName").toString());
        lstApiMessages.setAdapter(adapter);
        lstApiMessages.setLayoutManager(new LinearLayoutManager(this));

        // Set observer on the data in the viewModel. when viewModel data will change,
        // the method will activate.
        viewModel.get().observe(this, apiMessages -> {
            adapter.setMessages(apiMessages);
        });

    }
}