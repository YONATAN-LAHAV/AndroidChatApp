package com.example.whatsapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp.api.UserAPI;
import com.example.whatsapp.entities.ApiContact;

import java.util.LinkedList;
import java.util.List;

public class ApiContactRepository {
    //    private ApiContactDao dao;
    private UserAPI api;
    private ApiContactsListData _apiContactsListData;

    public ApiContactRepository() {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.apiContactDao();
        api = new UserAPI();
        _apiContactsListData = new ApiContactsListData();
    }

    private class ApiContactsListData extends MutableLiveData<List<ApiContact>> {
        public ApiContactsListData() {
            super();

//            api.get(this);
            List<ApiContact> apiContacts = new LinkedList<>();
            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("admin", "admin", "localhost", "Hey", "10:00"));
//            apiContacts.add(new ApiContact("yonatan", "yonatan", "localhost", "Heeeeeeeeeee", "10:00"));
//            apiContacts.add(new ApiContact("roy", "roy", "localhost", "Hellooo", "10:00"));
//            apiContacts.add(new ApiContact("liron", "liron", "localhost", "Hey", "10:00"));
//            setValue(apiContacts);
            setValue(apiContacts);
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserAPI userAPI = new UserAPI();
                userAPI.get(this);
            }).start();
//            new Thread(()->{
//                _apiContactsListData.postValue(dao.get());
//            }).start();
        }
    }

    public LiveData<List<ApiContact>> getAll() {
        return _apiContactsListData;
    }
}
