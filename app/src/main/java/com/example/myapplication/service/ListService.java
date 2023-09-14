package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.MobileProductForList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListService {

    @GET("list/getMobileProductsForList")
    Call<List<MobileProductForList>> getMobileProductsForList();

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
