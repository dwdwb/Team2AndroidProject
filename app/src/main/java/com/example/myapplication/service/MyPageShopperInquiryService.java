package com.example.myapplication.service;

import com.example.myapplication.dto.ProductInquiry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyPageShopperInquiryService {
    @GET("myPageShopperInquiry/getInquiryProductList")
    Call<List<String>> getInquiryProductList(@Query("sno") int sno);

    @GET("myPageShopperInquiry/getInquiryList")
    Call<List<ProductInquiry>> getInquiryList(@Query("sno") int sno, @Query("productName") String productName);

    @POST("myPageShopperInquiry/deleteInquiry")
    Call<Void> deleteInquiry(@Body ProductInquiry productInquiry);
}
