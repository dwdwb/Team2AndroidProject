package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.viewHolder.CartCouponViewHolder;
import com.example.myapplication.viewHolder.CartProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CartCouponAdapter  extends RecyclerView.Adapter<CartCouponViewHolder> {
    List<Coupon> couponList = new ArrayList<>();

    OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public CartCouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cart_coupon_item, parent, false);
        CartCouponViewHolder cartCouponViewHolder = new CartCouponViewHolder(itemView, onItemClickListener);
        return cartCouponViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartCouponViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.setData(coupon);
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public void setCouponList(List<Coupon> list) {
        this.couponList = list;
    }

    public Coupon getItem(int position) {
        return couponList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
