package com.example.myapplication.service;

import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.AddressResult;
import com.example.myapplication.dto.Cart;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.dto.Order;
import com.example.myapplication.dto.OrderRequestBody;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.dto.ReceiptHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("defaultAddress")
    Call<AddressList> defaultAddress(@Query("shopper_no") int shopper_no);

    @POST("addOrder1")
    Call<Void> addOrder1(@Body Order order);

    @POST("addOrder2")
    Call<Void> addOrder2(@Body List<Coupon> couponList);

    @POST("addOrder3")
    Call<Void> addOrder3(@Body List<ReceiptHistory> ReceiptHistoryList);

    @POST("addOrder4")
    Call<Void> addOrder4(@Body List<Cart> CartList);
}
