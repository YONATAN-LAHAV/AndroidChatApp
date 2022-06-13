package com.example.whatsapp.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.AddContactActivity;
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
    LoginPostRequest _connectedUser;

    // Only for the Login.
    public UserAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    // For repositories.
    public UserAPI(LoginPostRequest connectedUser) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        _connectedUser = connectedUser;
    }

    /**
     * Login method.
     */
    public void login(String username, String password, Context context) {
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

    /**
     * Get all contacts of the connected user.
     */
    public void get(MutableLiveData<List<ApiContact>> apiContacts) {
        Call<List<ApiContact>> call = webServiceAPI.GetAllContacts(_connectedUser.getId());
        call.enqueue(new Callback<List<ApiContact>>() {
            @Override
            public void onResponse(Call<List<ApiContact>> call, Response<List<ApiContact>> response) {
                new Thread(() -> {
                    apiContacts.postValue(response.body());
                }).start();

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

    public void addNewContact(ApiContact apiContact) {
        ContactsPostRequest contactsPostRequest = new ContactsPostRequest(
                apiContact.getId(),
                apiContact.getName(),
                apiContact.getServer()
        );
        Call<Void> call = webServiceAPI.CreateContact(contactsPostRequest, _connectedUser.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}