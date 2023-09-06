package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOrderHistoryBinding;
import com.example.myapplication.databinding.FragmentShippingBinding;

public class ShippingFragment extends Fragment {
    private static final String TAG = "ShippingFragment";
    private FragmentShippingBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShippingBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnAddShipping();
        initBtnMyPage();
        initBtnSearch();
        initBtnMain();
        initBtnCart();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnAddShipping() {
        binding.btnAddShipping.setOnClickListener(v -> {
            navController.navigate(R.id.action_shipping_to_addShipping);
        });
    }

    private void initBtnMyPage() {
        binding.btnMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_shipping_to_search);
        });
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_shipping_to_cart);
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}