package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnToLoginScreen = findViewById(R.id.btnToLoginScreen);
        Button btnToRegisterScreen = findViewById(R.id.btnToRegisterScreen);
        Button btnToContacts = findViewById(R.id.btnToContacts);

        btnToLoginScreen.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnToRegisterScreen.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        btnToContacts.setOnClickListener(view -> {
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
        });

    }
}