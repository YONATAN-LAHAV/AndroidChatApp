package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsapp.api.UserAPI;
import com.example.whatsapp.entities.ContactsPostRequest;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


//        Bundle extras = getIntent().getExtras();
//        TextView tvErr = findViewById(R.id.tvErr);
//        if (extras != null)
//            tvErr.setText(extras.getString("Invalid"));
//        else
//            tvErr.setText("");
//        UserAPI api = new UserAPI(this, extras.get("username").toString());
//
//        Button btnAddContact = findViewById(R.id.btnAddContact);
//        btnAddContact.setOnClickListener(view -> {
//
//            EditText etAddContactUsername = findViewById(R.id.etAddContactUsername);
//            EditText etAddContacNickname = findViewById(R.id.etAddContacNickname);
//            EditText etAddContactServer = findViewById(R.id.etAddContactServer);
//            ContactsPostRequest contact =
//                    new ContactsPostRequest(etAddContactUsername.getText().toString(),
//                            etAddContacNickname.getText().toString()
//                            , etAddContactServer.getText().toString());
//            api.CreateContact(contact, extras.get("username").toString(),extras.get("server").toString());
//            finish();
//        });

    }
}