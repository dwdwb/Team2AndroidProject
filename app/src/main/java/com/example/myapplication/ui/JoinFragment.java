package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentJoinBinding;
import com.example.myapplication.databinding.FragmentLoginBinding;


public class JoinFragment extends Fragment {

    private FragmentJoinBinding binding;

    private NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);



        initBtnList();
        return binding.getRoot();
    }

    private void  initBtnList() {
        binding.btnJoin.setOnClickListener(v -> {
            navController.popBackStack();

        });

    }

}