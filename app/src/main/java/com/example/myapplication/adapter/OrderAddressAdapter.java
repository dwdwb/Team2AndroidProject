package com.example.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.viewHolder.AddressViewHolder;
import com.example.myapplication.viewHolder.OrderAddressViewHolder;
import com.example.myapplication.viewHolder.OrderViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderAddressAdapter extends RecyclerView.Adapter<OrderAddressViewHolder> {
    private static final String TAG = "OrderAddressAdapter";
    private List<AddressList> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public OrderAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_order_address_item, parent, false);
        OrderAddressViewHolder orderAddressViewHolder = new OrderAddressViewHolder(itemView, onItemClickListener);
        return orderAddressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAddressViewHolder holder, int position) {
        AddressList addressList = list.get(position);
        Log.i(TAG, addressList.toString());
        holder.setData(addressList);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<AddressList> list) {
        this.list = list;
    }

    public AddressList getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onDeleteClick(View itemView, int position);
        void onSelectClick(View itemView, int position);
    }

    public void setOnItemClickListener(OrderAddressAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
