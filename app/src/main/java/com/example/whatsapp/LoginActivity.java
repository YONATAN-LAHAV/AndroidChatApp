package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsapp.api.LoginApi;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginApi api = new LoginApi();


        Bundle extras = getIntent().getExtras();
        TextView tvErr = findViewById(R.id.tvErr);
        if (extras != null)
            tvErr.setText(extras.getString("Invalid"));
        else
            tvErr.setText("");

        // Login logic.
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {
            EditText etUsername = findViewById(R.id.etUsername);
            EditText etPassword = findViewById(R.id.etPassword);
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            api.login(username, password, this);
        });


        // Go to register activity.
        Button btnToRegister = findViewById(R.id.btnToRegister);
        btnToRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}