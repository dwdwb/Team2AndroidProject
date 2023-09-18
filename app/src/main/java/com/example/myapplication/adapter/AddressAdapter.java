package com.example.myapplication.adapter;

import android.location.Address;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.AddressList;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.MyPageMenuItem;
import com.example.myapplication.viewHolder.AddressViewHolder;
import com.example.myapplication.viewHolder.ListViewHolder;
import com.example.myapplication.viewHolder.MyPageMenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressViewHolder> {
    private static final String TAG = "AddressAdapter";
    private List<AddressList> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_address_item, parent, false);
        AddressViewHolder addressViewHolder = new AddressViewHolder(itemView, onItemClickListener);
        return addressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
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

    public void setOnItemClickListener(AddressAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
