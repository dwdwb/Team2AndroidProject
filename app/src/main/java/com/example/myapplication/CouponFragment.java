package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentCouponBinding;
import com.example.myapplication.databinding.FragmentShippingBinding;

public class CouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";
    private FragmentCouponBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCouponBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnMain();
        initBtnSearch();
        initBtnCart();
        initBtnMyPage();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_coupon_to_search);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_coupon_to_cart);
        });
    }

    private void initBtnMyPage() {
        binding.btnMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}