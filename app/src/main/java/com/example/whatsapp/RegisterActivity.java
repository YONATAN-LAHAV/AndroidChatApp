package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.whatsapp.api.RegisterApi;
import com.example.whatsapp.databinding.ActivityRegisterBinding;
import com.example.whatsapp.entities.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Create binding object in order to replace "findViewById(R.id.btnToLogin)".
        ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Validation message.
        Bundle extras = getIntent().getExtras();
        TextView tvErr = findViewById(R.id.tvErr);
        if (extras != null)
            tvErr.setText(extras.getString("Invalid"));
        else
            tvErr.setText("");

        // Create RegisterApi object.
        RegisterApi api = new RegisterApi();

        //bind to btnRegister.
        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            String nickname = binding.etNickname.getText().toString();
            User user = new User(username, password, nickname);
            api.register(user, this);
        });

        // Nevigate to the login screen.
        binding.btnToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}