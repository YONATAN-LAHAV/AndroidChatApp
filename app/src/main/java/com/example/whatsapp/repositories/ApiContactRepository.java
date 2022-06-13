package com.example.whatsapp.repositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.api.ContactsApi;
import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.ContactsPostRequest;
import com.example.whatsapp.entities.LoginPostRequest;

import java.util.LinkedList;
import java.util.List;

public class ApiContactRepository {
    //    private ApiContactDao dao;


    private ContactsApi _api;
    private ApiContactsListData _apiContactsListData;
    private LoginPostRequest _connectedUser;


    public ApiContactRepository(LoginPostRequest connectedUser) {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.apiContactDao();
        _connectedUser = connectedUser;
        _apiContactsListData = new ApiContactsListData();
        _api = new ContactsApi(_connectedUser, _apiContactsListData);
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
                _api.get();
            }).start();

//            new Thread(()->{
//                _apiContactsListData.postValue(dao.get());
//            }).start();
        }
    }

    public LiveData<List<ApiContact>> getAll() {
        return _apiContactsListData;
    }

    public void add(ContactsPostRequest contactsPostRequest, AppCompatActivity appCompatActivity) {
        _api.add(contactsPostRequest,appCompatActivity);
    }

//    public void add(ApiContact apiContact) {
//        api.addNewContact(apiContact);
//    }
}
