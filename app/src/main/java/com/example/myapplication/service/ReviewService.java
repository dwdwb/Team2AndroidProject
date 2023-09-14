package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.ReviewListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReviewService {
    @GET("review/getReviewList")
    Call<List<ReviewListItem>> getReviewList();

    static void loadThumbnailImage(int board_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "list/getThumbnailImage?board_no=" + board_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
