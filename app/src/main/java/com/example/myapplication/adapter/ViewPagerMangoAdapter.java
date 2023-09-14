package com.example.myapplication.adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.ui.ViewPagerMangoFragment;
import com.example.myapplication.ui.ViewPagerWatermelonFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerMangoAdapter extends FragmentStateAdapter {
    private static final String TAG = "ViewPagerMangoAdapter";
    private List<MobileProductForList> list = new ArrayList<>();

    public ViewPagerMangoAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "실행");
        ViewPagerMangoFragment viewPagerMangoFragment= new ViewPagerMangoFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("productNo", (list.get(position).getProduct_no()));
        bundle.putString("productName", (list.get(position).getProduct_name()));
        bundle.putString("productPrice", (list.get(position).getProduct_price()));
        bundle.putString("discountRate", (list.get(position).getDiscount_rate()));
        bundle.putString("discountPrice", (list.get(position).getDiscount_price()));
        bundle.putFloat("starRate", ((float)list.get(position).getStar_rate()));
        viewPagerMangoFragment.setArguments(bundle);

        return viewPagerMangoFragment;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MobileProductForList> list) {
        this.list = list;
    }
}
