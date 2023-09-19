package com.example.myapplication.ui;

import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentWriteReviewBinding;
import com.example.myapplication.dto.WriteReviewResult;
import com.example.myapplication.service.ReviewService;
import com.example.myapplication.service.ServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteReviewFragment extends Fragment {
    private static final String TAG = "WriteReviewFragment";
    private FragmentWriteReviewBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWriteReviewBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        Bundle bundle = getArguments();
        int order_no = bundle.getInt("order_no");
        int product_no = bundle.getInt("product_no");


        initRatingBar();
        initBtnReview(order_no, product_no);
        initBtnBack();

        return binding.getRoot();
    }

    public void initRatingBar() {
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                binding.ratingBar.setRating(rating);
            }
        });
    }

   private void initBtnReview(int order_no, int product_no) {
       binding.btnReview.setOnClickListener(v -> {
           ReviewService reviewService = ServiceProvider.getReviewService(getContext());

           String reviewContent = binding.reviewContent.getText().toString();
           int starRating = (int) binding.ratingBar.getRating();

           Call<WriteReviewResult> call = reviewService.writeReview(order_no, product_no, product_no, starRating, reviewContent);
           call.enqueue(new Callback<WriteReviewResult>() {
               @Override
               public void onResponse(Call<WriteReviewResult> call, Response<WriteReviewResult> response) {
                   WriteReviewResult writeReviewResult = response.body();
                   if (writeReviewResult.getResult().equals("success")) {
                       navController.popBackStack(R.id.myPage, false);
                       navController.navigate(R.id.action_myPage_to_review);
                   } else {
                       AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                               .setTitle("글쓰기 실패")
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