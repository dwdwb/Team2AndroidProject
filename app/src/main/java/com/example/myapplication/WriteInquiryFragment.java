package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentWriteInquiryBinding;

public class WriteInquiryFragment extends Fragment {
    private static final String TAG = "WriteInquiryFragment";
    private FragmentWriteInquiryBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWriteInquiryBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);

        initBtnDetail();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnDetail() {
        binding.btnDetail.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}