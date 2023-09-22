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

    @POST("addAddress")
    Call<AddressResult> addAddress(
            @Query("shopper_no") int shopper_no,

            @Query("shipping_name") String shipping_name,
            @Query("shipping_address") String shipping_address,
            @Query("receiver_tel") String receiver_tel,
            @Query("shipping_preference") String shipping_preference);

    @POST("modifyAddress")
    Call<Void> modifyAddress(
            @Query("shopper_no") int shopper_no,
            @Query("address_no") int address_no,
            @Query("shipping_name") String shipping_name,
            @Query("shipping_address") String shipping_address,
            @Query("receiver_tel") String receiver_tel,
            @Query("shipping_preference") String shipping_preference);

}
