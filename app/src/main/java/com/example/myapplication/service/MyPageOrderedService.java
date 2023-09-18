package com.example.myapplication.service;

import com.example.myapplication.dto.Coupon;
import com.example.myapplication.dto.OrderHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyPageOrderedService {

    @GET("getOrderedList")
    Call<List<OrderHistory>> getOrderedList();

    @GET("searchOrderedList")
    Call<List<OrderHistory>> searchOrderedList(
            @Query("shopperNo") int shopperNo,
            @Query("searchKeyword") String searchKeyword
    );
}
