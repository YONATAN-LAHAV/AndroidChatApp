package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.whatsapp.databinding.ActivityRegisterBinding;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private AppDB db;
    private ActivityRegisterBinding binding;
    private User user;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create binding object in order to replace "findViewById(R.id.btnToLogin)".
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create db.
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class
                        , "ChatAppDB")
                .allowMainThreadQueries()
                .build();
        // Create userDao.
        userDao = db.UserDao();
        //bind to btnRegister.
        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            String nickname = binding.etNickname.getText().toString();
            user = new User(username, password, nickname);
            userDao.insert(user);
        });

         List<UserWithConversation> userArray = userDao.index();

//        if (db.UserDao().get("admin") == null)
//            binding.btnRegister.setActivated(false);
        Button btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}