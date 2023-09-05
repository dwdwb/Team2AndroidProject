package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentAddShippingBinding;
import com.example.myapplication.databinding.FragmentCartBinding;

public class AddShippingFragment extends Fragment {
    private static final String TAG = "AddShippingFragment";
    private FragmentAddShippingBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddShippingBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnAddShipping();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnAddShipping() {
        binding.btnAddShipping.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}