package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp.api.RegisterApi;
import com.example.whatsapp.databinding.ActivityRegisterBinding;
import com.example.whatsapp.entities.User;
import com.example.whatsapp.localdb.*;

import java.util.List;

public class RegisterActivity extends Activity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private ActivityRegisterBinding binding;
    private boolean isImageUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Validation message.
        Bundle extras = getIntent().getExtras();
        TextView tvErr = findViewById(R.id.tvErr);
        if (extras != null)
            tvErr.setText(extras.getString("Invalid"));
        else
            tvErr.setText("");

        // Create RegisterApi object.
        RegisterApi api = new RegisterApi(localDatabase.getInstance().usersDao());

        //bind to btnRegister.
        binding.btnRegister.setOnClickListener(view -> {
            boolean invalidPassword = false;
            boolean empty = false;
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            String confirmPassword = binding.etVerPassword.getText().toString();
            String nickname = binding.etNickname.getText().toString();
            User user = new User(username, password, nickname);

            // check if all fields full.
            if (binding.etUsername.length() == 0 || binding.etPassword.length() == 0 ||
                    binding.etVerPassword.length() == 0 ||binding.etNickname.length() == 0) {
                empty = true;
            }

            // check if confirm password match.
            else if (! password.equals(confirmPassword)) {
                invalidPassword = true;
            }
            api.register(user, this, empty, invalidPassword, isImageUpload);
        });



        // Nevigate to the login screen.
        binding.btnToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Nevigate to the login screen.
        binding.btnUploadImage.setOnClickListener(view -> {
            isImageUpload = false;
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            isImageUpload = true;
            Uri selectedImage = data.getData();
            binding.imageToUpload.setImageURI(selectedImage);
        }
    }
}