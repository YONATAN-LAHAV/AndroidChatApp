package com.example.whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.ApiMessage;
import com.example.whatsapp.repositories.ApiContactRepository;
import com.example.whatsapp.repositories.ApiMessageRepository;

import java.util.List;

public class ApiMessageViewModel extends ViewModel {
    private final ApiMessageRepository _apiMessageRepository;
    private final LiveData<List<ApiMessage>> _apiMessages;

    public ApiMessageViewModel() {
        _apiMessageRepository = new ApiMessageRepository();
        _apiMessages = _apiMessageRepository.getAll();

    }

    public LiveData<List<ApiMessage>> get() {
        return _apiMessages;
    }

//    public void add(ApiMessage apiMessage) {
//        _apiMessageRepository.add(apiMessage);
//    }
//
//    public void delete(ApiMessage apiMessage) {
//        _apiMessageRepository.delete(apiMessage);
//    }
//
//    public void reload() {
//        _apiMessageRepository.reload();
//    }

}
