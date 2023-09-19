package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.InquiryProductAdapter;

public class InquiryProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "InquiryProductViewHolder";
    private TextView product_name;
    private RecyclerView recycler_view;

    public InquiryProductViewHolder(@NonNull View itemView, InquiryProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        product_name = (TextView) itemView.findViewById(R.id.product_name);
        recycler_view = (RecyclerView) itemView.findViewById(R.id.recycler_view);
    }

    public void setData(String productName) {
        product_name.setText(productName);
    }

    public RecyclerView getRecycler_view() {
        return recycler_view;
    }

    public void setRecycler_view(RecyclerView recycler_view) {
        this.recycler_view = recycler_view;
    }
}
