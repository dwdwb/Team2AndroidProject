package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.databinding.FragmentSearchBinding;

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private FragmentListBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnDetail();
        initBtnSearch();
        initBtnMain();
        initBtnCart();
        initBtnBack();

        return binding.getRoot();
    }

    private void initBtnDetail() {
        binding.btnDetail.setOnClickListener(v -> {
            navController.navigate(R.id.action_list_to_detail);
        });
    }

    private void initBtnSearch() {
        binding.btnSearch.setOnClickListener(v -> {
            navController.navigate(R.id.action_list_to_search);
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
            navController.navigate(R.id.action_list_to_cart);
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }
}