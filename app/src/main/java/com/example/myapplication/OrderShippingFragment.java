package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentOrderBinding;
import com.example.myapplication.databinding.FragmentOrderShippingBinding;


public class OrderShippingFragment extends Fragment {

    private FragmentOrderShippingBinding binding;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrderShippingBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnShippingAdd();
        return binding.getRoot();
    }

    private void initBtnShippingAdd() {
        binding.btnShippingAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_orderShipping_to_addShipping);

        });
    }

}