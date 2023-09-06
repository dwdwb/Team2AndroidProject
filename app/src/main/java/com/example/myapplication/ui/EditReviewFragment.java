package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentEditReviewBinding;
import com.example.myapplication.databinding.FragmentWriteReviewBinding;

public class EditReviewFragment extends Fragment {
    private static final String TAG = "EditReviewFragment";
    private FragmentEditReviewBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditReviewBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnReview();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnReview() {
        binding.btnReview.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}