package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
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

        return binding.getRoot();
    }


}