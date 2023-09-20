package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.dto.WishListItem;
import com.example.myapplication.viewHolder.ReviewViewHolder;
import com.example.myapplication.viewHolder.WishViewHolder;

import java.util.ArrayList;
import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishViewHolder> {
    private List<WishListItem> list = new ArrayList<>();
    OnItemClickListener onItemClickListener1;
    OnItemClickListener onItemClickListener2;

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.wish_product_item, parent, false);
        WishViewHolder wishViewHolder = new WishViewHolder(itemView, onItemClickListener1, onItemClickListener2);
        return wishViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        WishListItem wishListItem = list.get(position);
        holder.setData(wishListItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<WishListItem> list) {
        this.list = list;
    }

    public WishListItem getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener1(OnItemClickListener onItemClickListener) {
        this.onItemClickListener1 = onItemClickListener;
    }
    public void setOnItemClickListener2(OnItemClickListener onItemClickListener) {
        this.onItemClickListener2 = onItemClickListener;
    }
}
