package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.DetailProductImageFragment;

public class DetailImagePagerAdapter extends FragmentStateAdapter {
    private static final String TAG = "DetailImagePagerAdapter";

    public DetailImagePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        DetailProductImageFragment detailProductImageFragment = new DetailProductImageFragment();

        return detailProductImageFragment;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
