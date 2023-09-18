package com.example.myapplication.ui;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentViewPagerMangoBinding;
import com.example.myapplication.databinding.FragmentViewPagerWatermelonBinding;
import com.example.myapplication.service.ListService;

public class ViewPagerMangoFragment extends Fragment {
    private static final String TAG = "ViewPagerMangoFragment";
    FragmentViewPagerMangoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewPagerMangoBinding.inflate(inflater);

        //전달 데이터 받고 fragment 구성
        Bundle bundle = getArguments();
        ListService.loadThumbnailImage(bundle.getInt("productNo"), binding.productImage);
        binding.productName.setText(bundle.getString("productName"));
        binding.discountRate.setText(bundle.getString("discountRate"));
        binding.productPrice.setText(bundle.getString("productPrice"));
        binding.productPrice.setPaintFlags(binding.productPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        binding.discountPrice.setText(bundle.getString("discountPrice"));
        //binding.ratingBar.setRating(bundle.getFloat("starRate"));

        initBtn(bundle.getInt("productNo"));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initBtn(int board_no) {
        binding.wholeButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("board_no", board_no);

            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}