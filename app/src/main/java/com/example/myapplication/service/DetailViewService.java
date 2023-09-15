package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.dto.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailViewService {
    @GET("detailView/getBoard")
    Call<ProductBoard> getBoard(@Query("bno") int bno);

    static void loadProductImage(int productNo, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "detailView/getProductImage?bno=" + productNo;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @GET("detailView/getMediaNoList")
    Call<List<Integer>> getMediaNoList(@Query("bno") int bno);

    static void loadContentImage(int media_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "detailView/getContentImage?mno=" + media_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @GET("detailView/getInquiryList")
    Call<List<ProductInquiry>> getInquiryList(@Query("bno") int bno);

    @GET("detailView/getReviewList")
    Call<List<Review>> getReviewList(@Query("bno") int bno);
}
