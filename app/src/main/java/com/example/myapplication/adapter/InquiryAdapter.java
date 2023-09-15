package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.viewHolder.CartProductViewHolder;
import com.example.myapplication.viewHolder.DetailInquiryViewHolder;
import com.example.myapplication.viewHolder.InquiryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class InquiryAdapter extends RecyclerView.Adapter<InquiryViewHolder> {
    private List<ProductInquiry> productInquiryList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public InquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.inquiry_item, parent, false);
        InquiryViewHolder inquiryViewHolder = new InquiryViewHolder(itemView, onItemClickListener);
        return inquiryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InquiryViewHolder holder, int position) {
        ProductInquiry productInquiry = productInquiryList.get(position);
        holder.setData(productInquiry);
    }

    @Override
    public int getItemCount() {
        return productInquiryList.size();
    }

    public void setList(List<ProductInquiry> list) {
        this.productInquiryList = list;
    }

    public ProductInquiry getItem(int position) {
        return productInquiryList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(InquiryAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
