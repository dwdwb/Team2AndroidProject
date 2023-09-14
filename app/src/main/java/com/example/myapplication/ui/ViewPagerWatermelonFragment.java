package com.example.myapplication.ui;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentViewPagerCherryBinding;
import com.example.myapplication.databinding.FragmentViewPagerWatermelonBinding;
import com.example.myapplication.service.ListService;

public class ViewPagerWatermelonFragment extends Fragment {
    private static final String TAG = "ViewPagerWatermelonFrag";
    FragmentViewPagerWatermelonBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewPagerWatermelonBinding.inflate(inflater);

        //전달 데이터 받고 fragment 구성
        Bundle bundle = getArguments();
        ListService.loadThumbnailImage(bundle.getInt("productNo"), binding.productImage);
        binding.productName.setText(bundle.getString("productName"));
        binding.discountRate.setText(bundle.getString("discountRate"));
        binding.productPrice.setText(bundle.getString("productPrice"));
        binding.productPrice.setPaintFlags(binding.productPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        binding.discountPrice.setText(bundle.getString("discountPrice"));
        //binding.ratingBar.setRating(bundle.getFloat("starRate"));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}