package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMyPageBinding;

public class MyPageFragment extends Fragment {
    private static final String TAG = "MyPageFragment";
    private FragmentMyPageBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnOrderHistory();
        initBtnWish();
        initBtnCoupon();
        initBtnInquiry();
        initBtnReview();
        initBtnShpping();
        initBtnSearch();
        initBtnMain();
        initBtnCart();

        return binding.getRoot();
    }

    private void initBtnOrderHistory() {
        binding.btnOrderHistory.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_orderHistory);
        });
    }

    private void initBtnWish() {
        binding.btnWish.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_wish);
        });
    }

    private void initBtnCoupon() {
        binding.btnCoupon.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_coupon);
        });
    }

    private void initBtnInquiry() {
        binding.btnInquiry.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_inquiry);
        });
    }

    private void initBtnReview() {
        binding.btnReview.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_review);
        });
    }

    private void initBtnShpping() {
        binding.btnShipping.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_shipping);
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_search);
        });
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_myPage_to_cart);
        });
    }
}