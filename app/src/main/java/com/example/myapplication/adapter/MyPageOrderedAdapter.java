package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.OrderHistory;
import com.example.myapplication.viewHolder.MyPageCouponViewHolder;
import com.example.myapplication.viewHolder.MyPageOrderedViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyPageOrderedAdapter extends RecyclerView.Adapter<MyPageOrderedViewHolder> {

    private List<OrderHistory> orderedList = new ArrayList<>();


    OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MyPageOrderedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.my_page_ordered_item, parent, false);
        MyPageOrderedViewHolder myPageOrderedViewHolder = new MyPageOrderedViewHolder(itemView, onItemClickListener);
        return myPageOrderedViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyPageOrderedViewHolder holder, int position) {
        OrderHistory orderHistory = orderedList.get(position);
        holder.setData(orderHistory);
    }


    @Override
    public int getItemCount() {
        return orderedList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setOrderedList(List<OrderHistory> list) {this.orderedList = list;}

    public OrderHistory getItem(int position) {return orderedList.get(position); }

    public void setList(List<OrderHistory> list) {this.orderedList = list;}
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
