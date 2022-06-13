package com.example.whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.LoginPostRequest;
import com.example.whatsapp.repositories.ApiContactRepository;

import java.util.List;

public class ApiContactViewModel extends ViewModel {
    // Repository for cotacts.
    private ApiContactRepository _apiContactRepository;
    // LivaData with all the connected user contacts.
    private final LiveData<List<ApiContact>> _apiContacts;
    // Current connected user.
    private LoginPostRequest _connectedUser;

    /**
     * Constructor for ApiContactViewModel object, need to create factory and pass it
     * via ViewModelProvider in the activity.
     */
    public ApiContactViewModel(LoginPostRequest connectedUser) {
        _connectedUser = connectedUser;
        _apiContactRepository = new ApiContactRepository(_connectedUser);
        _apiContacts = _apiContactRepository.getAll();

    }

    /**
     * Get all contacts of the connected user as LiveData.
     * @return
     */
    public LiveData<List<ApiContact>> get() {
        return _apiContacts;
    }

    public void add(ApiContact apiContact) {
        _apiContactRepository.add(apiContact);
    }
//
//    public void delete(ApiContact apiContact) {
//        _apiContactRepository.delete(apiContact);
//    }
//
//    public void reload() {
//        _apiContactRepository.reload();
//    }

}
