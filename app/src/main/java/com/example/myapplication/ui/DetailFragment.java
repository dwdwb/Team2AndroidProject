package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);

        initBtnAddCart();
        initBtnOrder();
        initBtnWriteInquiry();
        initBtnMain();
        initBtnCart();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnAddCart() {
        binding.btnAddCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_cart);
        });
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_orderFragment);
        });
    }

    private void initBtnWriteInquiry() {
        binding.btnWriteInquiry.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_writeInquiry);
        });
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_cart);
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}