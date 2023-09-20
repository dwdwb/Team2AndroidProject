package com.example.myapplication.service;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dto.Review;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.dto.WishListItem;
import com.example.myapplication.dto.WriteReviewResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WishService {
    @GET("wish/getShopperWishList")
    Call<List<WishListItem>> getShopperWishList(@Query("shopper_id") String shopper_id);

    @GET("wish/deleteWish")
    Call<WriteReviewResult> deleteWish(@Query("product_no") int product_no, @Query("shopper_no") int shopper_no);

    @GET("wish/addToCart")
    Call<WriteReviewResult> addToCart(@Query("product_no") int product_no, @Query("shopper_no") int shopper_no);

    @GET("wish/isInWish")
    Call<WriteReviewResult> isInWish(@Query("product_no") int product_no, @Query("shopper_id") String shopper_id);

    @GET("wish/putInWishList")
    Call<WriteReviewResult> putInWishList(@Query("product_no") int product_no, @Query("shopper_id") String shopper_id);

    @GET("wish/removeFromWishList")
    Call<WriteReviewResult> removeFromWishList(@Query("product_no") int product_no, @Query("shopper_id") String shopper_id);
    /*@GET("review/getReviewList")
    Call<List<ReviewListItem>> getReviewList();

    @GET("review/getShopperReviewList")
    Call<List<ReviewListItem>> getShopperReviewList(@Query("shopper_id") String shopper_id);

    @GET("review/getReview")
    Call<Review> getReview(@Query("review_no") int review_no);

    @POST("review/writeReview")
    Call<WriteReviewResult> writeReview(
            @Query("order_no") int order_no,
            @Query("product_no") int product_no,
            @Query("board_no") int board_no,
            @Query("star_rate") int starRating,
            @Query("content") String reviewContent);

    @POST("review/editReview")
    Call<WriteReviewResult> editReview(
            @Query("review_no") int review_no,
            @Query("star_rate") int starRating,
            @Query("content") String reviewContent);

    @GET("review/deleteReview")
    Call<WriteReviewResult> deleteReview(@Query("review_no") int review_no);

    static void loadThumbnailImage(int board_no, ImageView imageView) {
        String url = NetworkInfo.BASE_URL + "list/getThumbnailImage?board_no=" + board_no;
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }*/


}
