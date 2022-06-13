package com.example.whatsapp.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.MyApplication;
import com.example.whatsapp.R;
import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.ApiMessage;
import com.example.whatsapp.entities.ApiTransfer;
import com.example.whatsapp.entities.LoginPostRequest;
import com.example.whatsapp.entities.MessagePostRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageApi {
    Retrofit _retrofit;
    WebServiceAPI _webServiceAPI;
    LoginPostRequest _connectedUser;
    ApiContact _contact;
    MutableLiveData<List<ApiMessage>> _apiMessagesListData;

    public MessageApi(LoginPostRequest connectedUser
            , ApiContact contact
            , MutableLiveData<List<ApiMessage>> apiMessagesListData) {
        _retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        _webServiceAPI = _retrofit.create(WebServiceAPI.class);
        _connectedUser = connectedUser;
        _contact = contact;
        _apiMessagesListData = apiMessagesListData;
    }

    /**
     * Get all messages.
     */
    public void get() {
        Call<List<ApiMessage>> call = _webServiceAPI
                .GetAllContactMessage(_contact.getId().toString(), _connectedUser.getId());
        call.enqueue(new Callback<List<ApiMessage>>() {
            @Override
            public void onResponse(Call<List<ApiMessage>> call, Response<List<ApiMessage>> response) {
                _apiMessagesListData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ApiMessage>> call, Throwable t) {
            }
        });
    }

    public void transfer(MessagePostRequest messagePostRequest, AppCompatActivity appCompatActivity) {
        Call<Void> call = _webServiceAPI
                .PostTransfer(new ApiTransfer(_connectedUser.getId()
                        , _contact.getId()
                        , messagePostRequest.getContent()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    add(messagePostRequest, appCompatActivity);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void add(MessagePostRequest messagePostRequest, AppCompatActivity appCompatActivity) {
        Call<ApiMessage> call = _webServiceAPI
                .CreateMessage(new MessagePostRequest(messagePostRequest.getContent())
                        , _contact.getId(), _connectedUser.getId());
        call.enqueue(new Callback<ApiMessage>() {
            @Override
            public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                new Thread(() -> {
//                    List<ApiMessage> apiMessageList = _apiMessagesListData.getValue();
//                    ApiMessage newMessage = response.body();
//                    newMessage.set_sent(true);
//                    apiMessageList.add(newMessage);
                    get();
                }).start();
            }

            @Override
            public void onFailure(Call<ApiMessage> call, Throwable t) {

            }
        });
    }
}
