package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.MobileProductForList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListService {

    @GET("list/getMobileProductsForList")
    Call<List<MobileProductForList>> getMobileProductsForList(@Query("keyword") String keyword);

    @GET("list/getMobileProductsForListPriceDesc")
    Call<List<MobileProductForList>> getMobileProductsForListPriceDesc(@Query("keyword") String keyword);

    @GET("list/getMobileProductsForListPriceAsc")
    Call<List<MobileProductForList>> getMobileProductsForListPriceAsc(@Query("keyword") String keyword);

    @GET("list/getCherryAdList")
    Call<List<MobileProductForList>> getCheryAdList();

    @GET("list/getWatermelonAdList")
    Call<List<MobileProductForList>> getWatermelonAdList();

    @GET("list/getMangoAdList")
    Call<List<MobileProductForList>> getMangoAdList();

    static void loadThumbnailImage(int board_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "list/getThumbnailImage?board_no=" + board_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
