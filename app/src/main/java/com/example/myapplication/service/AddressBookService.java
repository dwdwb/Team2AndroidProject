package com.example.myapplication.service;

import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.AddressResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddressBookService {
    @GET("getAddressList")
    Call<List<AddressList>> getAddressList();

    @POST("deleteAddress")
    Call<AddressResult> deleteAddress(@Query("address_no") int address_no);

}
