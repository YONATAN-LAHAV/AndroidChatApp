package com.example.whatsapp.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.whatsapp.ContactsActivity;
import com.example.whatsapp.LoginActivity;
import com.example.whatsapp.MyApplication;
import com.example.whatsapp.entities.*;

import java.util.List;

import com.example.whatsapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.*;

public class UserAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    Context context;

    public UserAPI(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.context = context;
    }

    public void login(String username, String password) {
        Call<User> call = webServiceAPI.Login(new LoginPostRequest(username, password));
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    Intent intent = new Intent(context, ContactsActivity.class);
                    intent.putExtra("username", user.getId());
                    intent.putExtra("password", user.getPassword());
                    intent.putExtra("nickname", user.getNickname());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("Invalid", "Invalid username or password!");
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void get() {
        Call<List<ApiContact>> call = webServiceAPI.GetAllContacts("admin");
        call.enqueue(new Callback<List<ApiContact>>() {
            @Override
            public void onResponse(Call<List<ApiContact>> call, Response<List<ApiContact>> response) {

                List<ApiContact> contacts = response.body();


//                new Thread(() -> {
//                    dao.clear();
//                    dao.insertList(response.body());
//                    postListData.postValue(dao.get());
//                }).start();
            }

            @Override
            public void onFailure(Call<List<ApiContact>> call, Throwable t) {
                Log.d("myError", String.valueOf(t));
            }
        });
    }
}