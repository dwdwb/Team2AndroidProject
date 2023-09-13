package com.example.myapplication.adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.DetailExplainFragment;
import com.example.myapplication.ui.DetailInquiryFragment;
import com.example.myapplication.ui.DetailReviewFragment;

public class DetailPagerAdapter extends FragmentStateAdapter {
    private static final String TAG = "DetailPagerAdapter";

    /*public DetailPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }*/

    /*@NonNull
    @Override
    public Fragment createFragment(int position) {
        DetailExplainFragment detailExplainFragment = new DetailExplainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo", (position + 1));
        detailExplainFragment.setArguments(bundle);
        return detailExplainFragment;
    }*/

    public DetailPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DetailExplainFragment();
            case 1:
                return new DetailReviewFragment();
            case 2:
                return new DetailInquiryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;       // 페이지 수
    }
}
