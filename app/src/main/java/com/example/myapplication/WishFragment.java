package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentReviewBinding;
import com.example.myapplication.databinding.FragmentWishBinding;


public class WishFragment extends Fragment {
    private static final String TAG = "WishFragment";
    private FragmentWishBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnBack();
        initBtnMain();
        initBtnSearch();
        initBtnCart();
        initBtnMyPage();

        return binding.getRoot();
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.popBackStack();
        });
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.navigate(R.id.action_wish_to_search);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.navigate(R.id.action_wish_to_cart);
        });
    }

    private void initBtnMyPage() {
        binding.btnMyPage.setOnClickListener(v -> {
            //대상으로 이동, 백스택의 위쪽 대상으로 모두 제거
            navController.popBackStack();
        });
    }


}