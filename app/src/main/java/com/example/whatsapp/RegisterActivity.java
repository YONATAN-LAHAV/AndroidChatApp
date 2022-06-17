package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp.api.RegisterApi;
import com.example.whatsapp.databinding.ActivityRegisterBinding;
import com.example.whatsapp.entities.User;
import com.example.whatsapp.localdb.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
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

        // Nevigate to the login screen.
        binding.btnToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });



        //bind to btnRegister.
        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            String confirmPassword = binding.etVerPassword.getText().toString();
            String nickname = binding.etNickname.getText().toString();
            User user = new User(username, password, nickname);

            // check if all fields full.
            if (binding.etUsername.length() == 0 || binding.etPassword.length() == 0 ||
                    binding.etVerPassword.length() == 0 || binding.etNickname.length() == 0) {

                Intent intent = this.getIntent();
                this.getIntent()
                        .putExtra("Invalid", "Please fill all fields.");
                this.finish();
                this.overridePendingTransition(0, 0);
                this.startActivity(intent);
                this.overridePendingTransition(0, 0);
            }

            // check if confirm password match.
            else if (!password.equals(confirmPassword)) {
                Intent intent = this.getIntent();
                this.getIntent()
                        .putExtra("Invalid",
                                "Password and confirm password does not match.");
                this.finish();
                this.overridePendingTransition(0, 0);
                this.startActivity(intent);
                this.overridePendingTransition(0, 0);
            }

            // check if all image uploaded.
            else if (!isImageUpload) {
                Intent intent = this.getIntent();
                this.getIntent()
                        .putExtra("Invalid",
                                "Upload image.");
                this.finish();
                this.overridePendingTransition(0, 0);
                this.startActivity(intent);
                this.overridePendingTransition(0, 0);
            }

            // valid state
            else {
                Bitmap image = ((BitmapDrawable)binding.imageToUpload.getDrawable()).getBitmap();
                new UploadImage(image, this, user).execute();
            }
        });


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

    private class UploadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        User user;
        Activity activity;
        public UploadImage(Bitmap image, Activity activity, User user) {
            this.image = image;
            this.activity = activity;
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            // String representation the image
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            // Create RegisterApi object.
            RegisterApi api = new RegisterApi(localDatabase.getInstance().usersDao());

            // Send request to server and update room.
            api.register(user, activity, encodedImage);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            //Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
        }
    }
}