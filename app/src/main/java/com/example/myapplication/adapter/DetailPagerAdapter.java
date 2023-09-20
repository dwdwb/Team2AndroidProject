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
    private int bno;

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
                DetailExplainFragment detailExplainFragment = new DetailExplainFragment();
                detailExplainFragment.setBno(bno);
                return detailExplainFragment;
            case 1:
                DetailReviewFragment detailReviewFragment = new DetailReviewFragment();
                detailReviewFragment.setBno(bno);
                return detailReviewFragment;
            case 2:
                DetailInquiryFragment detailInquiryFragment = new DetailInquiryFragment();
                detailInquiryFragment.setBno(bno);
                return detailInquiryFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;       // 페이지 수
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }
}
