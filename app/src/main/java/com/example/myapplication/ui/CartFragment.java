package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnOrder();

        //하단바 숨기기
        hideBottomNavigation(true);

        return binding.getRoot();
    }

    //하단바 설정
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottomNavigationView);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            navController.navigate(R.id.action_cart_to_order);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //하단바 보이기
        hideBottomNavigation(false);
    }
}