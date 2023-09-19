package com.example.myapplication.ui;

import android.app.Service;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentEditReviewBinding;
import com.example.myapplication.databinding.FragmentWriteReviewBinding;
import com.example.myapplication.dto.Product;
import com.example.myapplication.dto.Review;
import com.example.myapplication.dto.WriteReviewResult;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ProductService;
import com.example.myapplication.service.ReviewService;
import com.example.myapplication.service.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReviewFragment extends Fragment {
    private static final String TAG = "EditReviewFragment";
    private FragmentEditReviewBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditReviewBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        Bundle bundle = getArguments();
        int review_no = bundle.getInt("review_no");
        int product_no = bundle.getInt("product_no");
        setData(review_no, product_no);

        initBtnReview(review_no);
        initBtnBack();

        return binding.getRoot();
    }

    private void setData(int review_no, int product_no) {
        ListService listService = ServiceProvider.getListService(getContext());
        ListService.loadThumbnailImage(product_no, binding.productImage);
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<Product> call = productService.getProduct(product_no);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                String productNameAndOption = product.getProduct_NAME() + ", " + product.getProduct_OPTION();
                binding.productNameAndOption.setText(productNameAndOption);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

        ReviewService reviewService = ServiceProvider.getReviewService(getContext());
        Call<Review> call2 = reviewService.getReview(review_no);
        call2.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                Review review = response.body();
                float starRating = (float)review.getStar_rate() * 0.05f;
                binding.ratingBar.setRating(starRating);
                binding.reviewContent.setText(review.getContent());
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });
    }

    private void initBtnReview(int review_no) {
        binding.btnReview.setOnClickListener(v -> {
            ReviewService reviewService = ServiceProvider.getReviewService(getContext());

            String reviewContent = binding.reviewContent.getText().toString();
            int starRating = (int) binding.ratingBar.getRating();

            Call<WriteReviewResult> call = reviewService.editReview(review_no, starRating, reviewContent);
            call.enqueue(new Callback<WriteReviewResult>() {
                @Override
                public void onResponse(Call<WriteReviewResult> call, Response<WriteReviewResult> response) {
                    WriteReviewResult writeReviewResult = response.body();
                    if (writeReviewResult.getResult().equals("success")) {
                        navController.popBackStack();
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("수정 실패")
                                .setPositiveButton("확인", null)
                                .create();
                        alertDialog.show();
                    }
                }

                @Override
                public void onFailure(Call<WriteReviewResult> call, Throwable t) {

                }
            });
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}