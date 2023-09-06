package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentOrderHistoryBinding;

public class OrderHistoryFragment extends Fragment {
    private static final String TAG = "OrderHistoryFragment";
    private FragmentOrderHistoryBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnWriteReview();
        initBtnMyPage();
        initBtnSearch();
        initBtnMain();
        initBtnCart();

        return binding.getRoot();
    }

    private void initBtnWriteReview() {
        binding.btnWriteReview.setOnClickListener(v -> {
            navController.navigate(R.id.action_orderHistory_to_writeReview);
        });
    }

    private void initBtnMyPage() {
        binding.btnMyPage.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_orderHistory_to_search);
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
            navController.navigate(R.id.action_orderHistory_to_cart);
        });
    }
}