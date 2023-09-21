package com.example.myapplication.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

        // EditText를 찾아서 포커스 설정
        //binding.search.requestFocus();
        //setShowKeyBoard();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        // EditText를 찾아서 포커스 설정
        binding.search.requestFocus();
        setShowKeyBoard();
    }

    private void initBtnList() {
        binding.btnList.setOnClickListener(v -> {
            String keyword = binding.search.getText().toString();
            Bundle args = new Bundle();
            args.putSerializable("keyword", keyword);

            navController.navigate(R.id.action_search_to_list, args);
        });

        binding.search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String keyword = binding.search.getText().toString();
                Bundle args = new Bundle();
                args.putSerializable("keyword", keyword);
                navController.navigate(R.id.action_search_to_list, args);
                return true;
            }
            return false;
        });
    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void setShowKeyBoard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.search, InputMethodManager.SHOW_IMPLICIT);
    }

}