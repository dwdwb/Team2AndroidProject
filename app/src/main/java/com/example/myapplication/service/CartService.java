package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.Coupon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartService {
    @GET("cart/getCartProductList")
    Call<List<CartProduct>> getCartProductList();

    static void loadCartProductImage(int product_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "cart/getCartProductImage?pno=" + product_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @GET("cart/getCartCouponList")
    Call<List<Coupon>> getCartCouponList();

    @POST("cart/deleteCartProduct")
    Call<Void> deleteCartProduct(@Body CartProduct cartProduct);

    @POST("cart/updateStockCartProduct")
    Call<Void> updateStockCartProduct(@Body CartProduct cartProduct);
}
