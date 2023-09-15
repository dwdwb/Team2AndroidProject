package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.viewHolder.CartProductViewHolder;
import com.example.myapplication.viewHolder.DetailInquiryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailInquiryAdapter extends RecyclerView.Adapter<DetailInquiryViewHolder> {
    private List<ProductInquiry> productInquiryList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public DetailInquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.detail_inquiry_item, parent, false);
        DetailInquiryViewHolder detailInquiryViewHolder = new DetailInquiryViewHolder(itemView, onItemClickListener);
        return detailInquiryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailInquiryViewHolder holder, int position) {
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
