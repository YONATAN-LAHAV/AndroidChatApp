package com.example.whatsapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.api.UserAPI;
import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.LoginPostRequest;

import java.util.LinkedList;
import java.util.List;

public class ApiContactRepository {
    //    private ApiContactDao dao;


    private UserAPI api;
    private ApiContactsListData _apiContactsListData;
    private LoginPostRequest _connectedUser;

    public ApiContactRepository(LoginPostRequest connectedUser) {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.apiContactDao();
        _connectedUser = connectedUser;
        api = new UserAPI(_connectedUser);
        _apiContactsListData = new ApiContactsListData();
    }

    private class ApiContactsListData extends MutableLiveData<List<ApiContact>> {
        public ApiContactsListData() {
            super();
            List<ApiContact> apiContacts = new LinkedList<>();
            setValue(apiContacts);
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                api.get(this);
            }).start();

//            new Thread(()->{
//                _apiContactsListData.postValue(dao.get());
//            }).start();
        }
    }

    public LiveData<List<ApiContact>> getAll() {
        return _apiContactsListData;
    }

    public void add(ApiContact apiContact) {
        api.addNewContact(apiContact);
    }
}
