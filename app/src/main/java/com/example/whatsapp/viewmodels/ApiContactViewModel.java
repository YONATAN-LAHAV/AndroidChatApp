package com.example.whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.repositories.ApiContactRepository;

import java.util.List;

public class ApiContactViewModel extends ViewModel {
    private  ApiContactRepository _apiContactRepository;
    private final LiveData<List<ApiContact>> _apiContacts;
    private String _username;

//    public void setUsername(String username) {
//        _username = username;
//        _apiContactRepository = new ApiContactRepository(_username);
//    }

    public ApiContactViewModel(String username) {
        _apiContactRepository = new ApiContactRepository(username);
        _apiContacts = _apiContactRepository.getAll();

    }

    public LiveData<List<ApiContact>> get() {
        return _apiContacts;
    }

//    public void add(ApiContact apiContact) {
//        _apiContactRepository.add(apiContact);
//    }
//
//    public void delete(ApiContact apiContact) {
//        _apiContactRepository.delete(apiContact);
//    }
//
//    public void reload() {
//        _apiContactRepository.reload();
//    }

}
