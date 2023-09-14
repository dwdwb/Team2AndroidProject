package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.viewHolder.CartProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductViewHolder> {
    private List<CartProduct> cartProductList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cart_product_item, parent, false);
        CartProductViewHolder cartProductViewHolder = new CartProductViewHolder(itemView, onItemClickListener);
        return cartProductViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position) {
        CartProduct cartProduct = cartProductList.get(position);
        holder.setData(cartProduct);
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public void setList(List<CartProduct> list) {
        this.cartProductList = list;
    }

    public CartProduct getItem(int position) {
        return cartProductList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
