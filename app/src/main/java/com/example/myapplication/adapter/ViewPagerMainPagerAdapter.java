package com.example.myapplication.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.ViewPagerMainPageFragment;

public class ViewPagerMainPagerAdapter extends FragmentStateAdapter {
    public ViewPagerMainPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ViewPagerMainPageFragment viewPagerMainPageFragment= new ViewPagerMainPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo", (position + 1));
        viewPagerMainPageFragment.setArguments(bundle);
        return viewPagerMainPageFragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
