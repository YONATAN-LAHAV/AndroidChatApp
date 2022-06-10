package com.example.whatsapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.entities.ApiMessage;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ApiMessageRepository {
    //    private ApiMessageDao dao;
//    private ApiMessageApi api;
    private ApiMessagesListData _apiMessagesListData;

    public ApiMessageRepository() {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.apiMessageDao();
//        api = new ApiMessageApi();
        _apiMessagesListData = new ApiMessagesListData();
    }

    private class ApiMessagesListData extends MutableLiveData<List<ApiMessage>> {
        public ApiMessagesListData() {
            super();
            List<ApiMessage> apiMessages = new LinkedList<>();
            for (int i = 0; i < 10; i++) {
                apiMessages.add(new ApiMessage("hello", "10:30", true));
                apiMessages.add(new ApiMessage("Hey", "10:30", false));
                apiMessages.add(new ApiMessage("How are You ??????", "10:30", true));
                apiMessages.add(new ApiMessage("goody goody", "10:30", false));
            }
            setValue(apiMessages);
//            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();

//            new Thread(()->{
//                _apiMessagesListData.postValue(dao.get());
//            }).start();
        }
    }

    public LiveData<List<ApiMessage>> getAll() {
        return _apiMessagesListData;
    }
}
