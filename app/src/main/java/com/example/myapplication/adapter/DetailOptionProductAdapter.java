package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.viewHolder.CartProductViewHolder;
import com.example.myapplication.viewHolder.DetailOptionProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailOptionProductAdapter extends RecyclerView.Adapter<DetailOptionProductViewHolder> {
    private List<ProductBoard> optionProductList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public DetailOptionProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.detail_option_product_item, parent, false);
        DetailOptionProductViewHolder detailOptionProductViewHolder = new DetailOptionProductViewHolder(itemView, onItemClickListener);
        return detailOptionProductViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOptionProductViewHolder holder, int position) {
        ProductBoard optionProduct = optionProductList.get(position);
        holder.setData(optionProduct);
    }

    @Override
    public int getItemCount() {
        return optionProductList.size();
    }

    public void setList(List<ProductBoard> list) {
        this.optionProductList = list;
    }

    public List<ProductBoard> getList() {
        return optionProductList;
    }

    public ProductBoard getItem(int position) {
        return optionProductList.get(position);
    }

    public interface OnItemClickListener {
        void onBtnDeleteClick(View itemView, int position);
        //void onBtnPlusClick(View itemView, int position);
        void onBtnPlusClick(TextView optionStock, TextView optionPrice, int position);
        void onBtnMinusClick(TextView optionStock, TextView optionPrice, int position);
    }

    public void setOnItemClickListener(DetailOptionProductAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
