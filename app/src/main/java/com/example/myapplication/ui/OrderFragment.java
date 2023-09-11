package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOrderBinding;


public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnOrderHistory();
        initBtnOrderShipping();

        initRadioGroup();
        return binding.getRoot();
    }

    private void initRadioGroup() {
        binding.radioGroup.addView(binding.radioButtonA);
        binding.radioGroup.addView(binding.radioButtonB);
        binding.radioGroup.addView(binding.radioButtonC);
        binding.radioGroup.addView(binding.radioButtonD);

    }

    private void initBtnOrderShipping() {
        binding.btnOrderShipping.setOnClickListener(v -> {
            navController.navigate(R.id.action_order_to_orderShipping);

        });
    }

    private void  initBtnOrderHistory() {
        binding.btnOrderHistory.setOnClickListener(v -> {
            navController.navigate(R.id.action_order_to_orderHistory);

        });

    }

}