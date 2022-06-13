package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsapp.adapters.ApiContactListAdapter;
import com.example.whatsapp.adapters.ApiMessageListAdapter;
import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.LoginPostRequest;
import com.example.whatsapp.entities.MessagePostRequest;
import com.example.whatsapp.viewmodels.ApiContactViewModel;
import com.example.whatsapp.viewmodels.ApiMessageViewModel;

public class ChatActivity extends AppCompatActivity {

    private ApiMessageViewModel viewModel;
    private LoginPostRequest _connectedUser;
    private ApiContact _contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Init connected user field.
        Bundle extras = getIntent().getExtras();
        _connectedUser = new LoginPostRequest(extras.getString("username")
                , extras.getString("password"));

        // Init contact field.
        _contact = new ApiContact(extras.getString("ContactUsername")
                , extras.getString("ContactName")
                , extras.getString("ContactServer")
                , ""
                , "");

        // Init top bar (contact card).
        TextView tvContactName = findViewById(R.id.tvContactName);
        tvContactName.setText(_contact.getName());

        // Init _viewModel field.
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ApiMessageViewModel(_connectedUser, _contact);
            }
        };
        viewModel = new ViewModelProvider(this, factory).get(ApiMessageViewModel.class);

        // RecycleView logic.
        RecyclerView lstApiMessages = findViewById(R.id.lstApiMessages);
        final ApiMessageListAdapter adapter = new ApiMessageListAdapter(this, getIntent().getExtras().get("ContactName").toString());
        lstApiMessages.setLayoutManager(new LinearLayoutManager(this));
        lstApiMessages.setAdapter(adapter);

        // Set observer on the data in the viewModel. when viewModel data will change,
        // the method will activate.
        viewModel.get().observe(this, apiMessages -> {
            adapter.setMessages(apiMessages);
        });

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(view -> {
            EditText etNewMessage = findViewById(R.id.etNewMessage);
            MessagePostRequest messagePostRequest =
                    new MessagePostRequest(etNewMessage.getText().toString());
            viewModel.add(messagePostRequest, this);
            try {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
            }
            lstApiMessages.scrollToPosition(viewModel.get().getValue().size() - 1);
        });
    }
}