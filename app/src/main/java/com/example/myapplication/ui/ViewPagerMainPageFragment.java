package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
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
        if (pageNo == 1) {
            binding.image.setImageResource(R.drawable.mainfruit1);
            binding.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (pageNo==2) {
            binding.image.setImageResource(R.drawable.mainfruit2);
            binding.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            binding.image.setImageResource(R.drawable.mainfruit3);
            binding.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}