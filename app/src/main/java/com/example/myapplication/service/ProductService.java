package com.example.myapplication.service;

import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
    @GET("product/getProduct")
    Call<Product> getProduct(@Query("product_no") int product_no);
}
