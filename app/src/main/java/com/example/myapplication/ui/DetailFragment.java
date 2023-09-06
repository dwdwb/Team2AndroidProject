package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnAddCart();
        initBtnOrder();
        initBtnWriteInquiry();
        initBtnMain();
        initBtnCart();
        initBtnBack();

        //메뉴 초기화
        initMenu();

        //하단바 숨기기
        hideBottomNavigation(true);

        return binding.getRoot();
    }

    //하단바 숨기기
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottomNavigationView);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.top_menu,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.main) {
                    navController.popBackStack(R.id.main, false);
                    return true;
                } else if (menuItem.getItemId() == R.id.cart) {
                    navController.navigate(R.id.action_detail_to_cart);
                    return true;
                }
                return false;
            }
        };

        getActivity().addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initBtnAddCart() {
        binding.btnAddCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_cart);
        });
    }

    private void initBtnOrder() {
        binding.btnOrder.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_orderFragment);
        });
    }

    private void initBtnWriteInquiry() {
        binding.btnWriteInquiry.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_writeInquiry);
        });
    }

    private void initBtnMain() {
        binding.btnMain.setOnClickListener(v -> {
            navController.popBackStack(R.id.main, false);
        });
    }

    private void initBtnCart() {
        binding.btnCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_detail_to_cart);
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //하단바 보이기
        hideBottomNavigation(false);
    }
}