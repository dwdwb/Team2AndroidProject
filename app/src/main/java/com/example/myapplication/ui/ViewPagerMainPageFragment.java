package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentViewPagerMainPageBinding;

public class ViewPagerMainPageFragment extends Fragment {
    private FragmentViewPagerMainPageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewPagerMainPageBinding.inflate(inflater);

        //전달 데이터 받기
        Bundle bundle = getArguments();
        int pageNo = bundle.getInt("pageNo");
        initUIByPageNo(pageNo);

        return binding.getRoot();
    }

    private void initUIByPageNo(int pageNo) {
        //binding.txtContent.setText(pageNo + " 페이지 내용");
    }
}