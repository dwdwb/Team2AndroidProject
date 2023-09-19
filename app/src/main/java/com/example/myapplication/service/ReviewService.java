package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.dto.WriteReviewResult;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ReviewService {
    @GET("review/getReviewList")
    Call<List<ReviewListItem>> getReviewList();

    @POST("review/writeReview")
    Call<WriteReviewResult> writeReview(
            @Query("order_no") int order_no,
            @Query("product_no") int product_no,
            @Query("board_no") int board_no,
            @Query("star_rate") int starRating,
            @Query("content") String reviewContent);

    static void loadThumbnailImage(int board_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "list/getThumbnailImage?board_no=" + board_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }


}
