package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentWithdrawBinding;

public class WithdrawFragment extends Fragment {
    private static final String TAG = "WithdrawFragment";
    private FragmentWithdrawBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWithdrawBinding.inflate(inflater);
        navController = NavHostFragment.findNavController(this);

        initWithdrawBtn();

        initBtnBack();

        return binding.getRoot();
    }

    private void initWithdrawBtn() {
        binding.withdrawBtn.setOnClickListener(v -> {

        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}