package com.example.myapplication.adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ServiceProvider;
import com.example.myapplication.ui.CherryAdFragment;
import com.example.myapplication.ui.ViewPagerCherryFragment;
import com.example.myapplication.ui.ViewPagerMainPageFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerCherryAdapter extends FragmentStateAdapter {
    private static final String TAG = "ViewPagerCherryAdapter";
    private List<MobileProductForList> list = new ArrayList<>();

    public ViewPagerCherryAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "실행");
        ViewPagerCherryFragment viewPagerCherryFragment= new ViewPagerCherryFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("productNo", (list.get(position).getProduct_no()));
        bundle.putString("productName", (list.get(position).getProduct_name()));
        bundle.putString("productPrice", (list.get(position).getProduct_price()));
        bundle.putString("discountRate", (list.get(position).getDiscount_rate()));
        bundle.putString("discountPrice", (list.get(position).getDiscount_price()));
        bundle.putFloat("starRate", ((float)list.get(position).getStar_rate()));
        viewPagerCherryFragment.setArguments(bundle);

        return viewPagerCherryFragment;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MobileProductForList> list) {
        this.list = list;
    }
}
