package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.viewHolder.ListViewHolder;
import com.example.myapplication.viewHolder.ReviewViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private List<ReviewListItem> list = new ArrayList<>();
    OnItemClickListener onItemClickListener1;
    OnItemClickListener onItemClickListener2;

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.review_item, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(itemView, onItemClickListener1, onItemClickListener2);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewListItem reviewListItem = list.get(position);
        holder.setData(reviewListItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ReviewListItem> list) {
        this.list = list;
    }

    public ReviewListItem getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener1(OnItemClickListener onItemClickListener) {
        this.onItemClickListener1 = onItemClickListener;
    }
    public void setOnItemClickListener2(OnItemClickListener onItemClickListener) {
        this.onItemClickListener2 = onItemClickListener;
    }
}
