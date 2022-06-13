package com.example.whatsapp.repositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.api.MessageApi;
import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.ApiMessage;
import com.example.whatsapp.entities.LoginPostRequest;
import com.example.whatsapp.entities.MessagePostRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ApiMessageRepository {
    //    private ApiMessageDao dao;
    private MessageApi _api;
    private ApiMessagesListData _apiMessagesListData;
    private LoginPostRequest _connectedUser;
    private ApiContact _contact;

    public ApiMessageRepository(LoginPostRequest connectedUser, ApiContact contact) {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.apiMessageDao();
        _connectedUser = connectedUser;
        _contact = contact;
        _apiMessagesListData = new ApiMessagesListData();
        _api = new MessageApi(_connectedUser, _contact, _apiMessagesListData);
    }

    private class ApiMessagesListData extends MutableLiveData<List<ApiMessage>> {
        public ApiMessagesListData() {
            super();
            List<ApiMessage> apiMessages = new LinkedList<>();
            setValue(apiMessages);
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                _api.get();
            }).start();

//            new Thread(()->{
//                _apiMessagesListData.postValue(dao.get());
//            }).start();
        }
    }

    public LiveData<List<ApiMessage>> getAll() {
        return _apiMessagesListData;
    }

    public void add(MessagePostRequest messagePostRequest, AppCompatActivity appCompatActivity) {
        _api.transfer(messagePostRequest, appCompatActivity);
    }
}
