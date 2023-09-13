package com.example.myapplication.service;

import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.MobileProductForList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AddressBookService {
    @GET("getAddressList")
    Call<List<AddressList>> getAddressList();
}
