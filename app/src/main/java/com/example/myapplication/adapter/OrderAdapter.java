package com.example.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.viewHolder.AddressViewHolder;
import com.example.myapplication.viewHolder.OrderViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private static final String TAG = "OrderAdapter";
    private List<ProductBoard> list = new ArrayList<>();
    private OrderAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.order_item, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(itemView, onItemClickListener);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ProductBoard productBoard = list.get(position);
        holder.setData(productBoard);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ProductBoard> list) {
        this.list = list;
    }

    public ProductBoard getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);

    }

    public void setOnItemClickListener(OrderAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
