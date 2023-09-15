package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCherryAdBinding;
import com.example.myapplication.databinding.FragmentDetailProductImageBinding;

public class DetailProductImageFragment extends Fragment {
    private static final String TAG = "DetailProductImageFragment";
    private FragmentDetailProductImageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailProductImageBinding.inflate(inflater);

        initViewPager();

        return binding.getRoot();
    }

    private void initViewPager() {

    }
}