package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.viewHolder.SpecialPriceViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SpecialPriceAdapter extends RecyclerView.Adapter<SpecialPriceViewHolder> {
    private List<MobileProductForList> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public SpecialPriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.special_price_item, parent, false);
        SpecialPriceViewHolder specialPriceViewHolder = new SpecialPriceViewHolder(itemView, onItemClickListener);
        return specialPriceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialPriceViewHolder holder, int position) {
        MobileProductForList mobileProductForList = list.get(position);
        holder.setData(mobileProductForList);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MobileProductForList> list) {
        this.list = list;
    }

    public MobileProductForList getItem(int position) {
        return list.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
