package com.example.myapplication.service;

import com.example.myapplication.dto.LoginResult;
import com.example.myapplication.dto.Shopper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShopperService {
    @POST("shopper/tryLogin")
    Call<LoginResult> tryLogin(@Query("shopperId") String shopperId, @Query("shopperPw") String shopperPw);

    @GET("getShopper")
    Call<Shopper> getShopper(@Query("shopperId") String shopperId);

}
