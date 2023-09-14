package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.viewHolder.MyPageCouponViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyPageCouponAdapter extends RecyclerView.Adapter<MyPageCouponViewHolder> {

    List<Coupon> couponList = new ArrayList<>();

    OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MyPageCouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.my_page_coupon_item, parent, false);
        MyPageCouponViewHolder myPageCouponViewHolder = new MyPageCouponViewHolder(itemView, onItemClickListener);
        return myPageCouponViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyPageCouponViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.setData(coupon);
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public void setCouponList(List<Coupon> list) {this.couponList = list;}

    public Coupon getItem(int position) {return couponList.get(position); }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
