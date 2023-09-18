package com.example.myapplication.service;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.dto.Review;
import com.example.myapplication.dto.ReviewInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailViewService {
    @GET("detailView/getBoard")
    Call<ProductBoard> getBoard(@Query("bno") int bno);

    static void loadMainImage(int bno, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "detailView/getMainImage?bno=" + bno;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @GET("detailView/getMediaNoList")
    Call<List<Integer>> getMediaNoList(@Query("bno") int bno);

    /*static void loadContentImage(int media_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "detailView/getContentImage?mno=" + media_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }*/

    static void loadContentImage(int media_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "detailView/getContentImage?mno=" + media_no;
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        // 이미지 로드가 완료된 후 실행되는 부분
                        int imageHeight = resource.getHeight();

                        // 이미지뷰에 이미지 설정
                        imageView.setImageBitmap(resource);

                        // 이미지뷰의 스케일 타입을 FIT_XY로 설정하여 이미지를 늘리거나 줄이지 않고 부모 크기에 맞춤
                        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                        // 이미지뷰의 높이를 이미지의 높이로 설정
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = imageHeight;
                        // 이미지뷰의 너비를 부모 크기에 딱 맞춤
                        //layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                        imageView.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // 이미지가 로드되지 못하고 클리어된 경우에 대한 처리
                    }
                });
    }

    @GET("detailView/getInquiryList")
    Call<List<ProductInquiry>> getInquiryList(@Query("bno") int bno);

    @GET("detailView/getReviewList")
    Call<List<Review>> getReviewList(@Query("bno") int bno);

    @GET("detailView/getReviewInfo")
    Call<ReviewInfo> getReviewInfo(@Query("bno") int bno);

    @GET("detailView/getOptionProductList")
    Call<List<ProductBoard>> getOptionProductList(@Query("productName") String productName);
}
