package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.viewHolder.OrderCartViewHolder;
import com.example.myapplication.viewHolder.OrderViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderCartAdapter extends RecyclerView.Adapter<OrderCartViewHolder>{
    private static final String TAG = "OrderCartAdapter";
    private List<CartProduct> list = new ArrayList<>();
    private com.example.myapplication.adapter.OrderCartAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public OrderCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.order_item, parent, false);
        OrderCartViewHolder orderCartViewHolder = new OrderCartViewHolder(itemView, onItemClickListener);
        return orderCartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCartViewHolder holder, int position) {
        CartProduct cartProduct = list.get(position);
        holder.setData(cartProduct);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<CartProduct> list) {
        this.list = list;
    }

    public CartProduct getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);

    }

    public void setOnItemClickListener(com.example.myapplication.adapter.OrderCartAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
