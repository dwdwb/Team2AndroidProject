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
import com.example.myapplication.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnLogin();
        initBtnList();
        initBtnDetail();
        initBtnMyPage();
        initBtnCart();
        initBtnSearch();

        return binding.getRoot();
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_to_login);
        });
    }

    private void initBtnList() {
        binding.btnList.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_to_list);
        });
    }

    private void initBtnDetail() {
        binding.btnDetail.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_to_detail);
        });
    }

    private void initBtnMyPage() {
        binding.btnMyPage.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_to_myPage);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_to_cart);
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_main_to_search);
        });
    }
}