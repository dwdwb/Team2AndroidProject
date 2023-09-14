package com.example.myapplication.service;

import com.example.myapplication.dto.Coupon;
import com.example.myapplication.dto.OrderHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyPageOrderedService {

    @GET("getOrderedList")
    Call<List<OrderHistory>> getOrderedList();
}
