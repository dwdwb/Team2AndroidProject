package com.example.myapplication.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.MyPageMenuItem;
import com.example.myapplication.viewHolder.MyPageMenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyPageMenuItemAdapter extends RecyclerView.Adapter<MyPageMenuItemViewHolder> {
    private List<MyPageMenuItem> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View viewItem, int position);
    }

    @NonNull
    @Override
    public MyPageMenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.my_page_menu_item, parent, false);
        MyPageMenuItemViewHolder myPageMenuItemViewHolder = new MyPageMenuItemViewHolder(itemView, onItemClickListener);

        return myPageMenuItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPageMenuItemViewHolder holder, int position) {
        MyPageMenuItem myPageMenuItem = list.get(position);
        holder.setData(myPageMenuItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MyPageMenuItem> list) {
        this.list = list;
    }

    public MyPageMenuItem getItem(int position) {
        return list.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
