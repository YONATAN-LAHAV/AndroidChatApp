package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.whatsapp.localdb.*;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.whatsapp.databinding.ActivityRegisterBinding;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private AppDB db;
    private ActivityRegisterBinding binding;
    private User user;

    // ------------------------------------------
    private UserDao userDao;
    private ContactDao contactDao;
    private MessageDao messageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create binding object in order to replace "findViewById(R.id.btnToLogin)".
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create db.
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDB.class
                        , "AppDB")
                .allowMainThreadQueries()
                .build();

        // Create userDao.
        userDao = db.userDao();
        messageDao = db.messageDao();
        contactDao = db.contactDao();

//        String path = getDatabasePath("AppDB").getAbsolutePath();

        //bind to btnRegister.
        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            String nickname = binding.etNickname.getText().toString();
            user = new User (username, password, "liron pic string");
            userDao.insert(user);
        });

//        List<UserWithConversation> userArray = userDao.index();

//        if (db.UserDao().get("admin") == null)
//            binding.btnRegister.setActivated(false);
        Button btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}