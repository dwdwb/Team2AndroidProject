package com.example.myapplication.service;

import com.example.myapplication.dto.Coupon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyPageCouponService {

    @GET("cart/getCartCouponList")
    Call<List<Coupon>> getCartCouponList();
}
