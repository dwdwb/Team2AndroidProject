package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMainBinding;
import com.example.myapplication.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnList();
        initBtnBack();

        return binding.getRoot();
    }

   private void initBtnList() {
        String keyword = binding.search.getText().toString();
        Bundle args = new Bundle();
        args.putString("keyword", keyword);

        binding.btnList.setOnClickListener(v -> {
            navController.navigate(R.id.action_search_to_list, args);
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

}