package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.dto.Review;
import com.example.myapplication.viewHolder.DetailInquiryViewHolder;
import com.example.myapplication.viewHolder.DetailReviewViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailReviewAdapter extends RecyclerView.Adapter<DetailReviewViewHolder> {
    private List<Review> reviewList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public DetailReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.detail_review_item, parent, false);
        DetailReviewViewHolder detailReviewViewHolder = new DetailReviewViewHolder(itemView, onItemClickListener);
        return detailReviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.setData(review);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void setList(List<Review> list) {
        this.reviewList = list;
    }

    public Review getItem(int position) {
        return reviewList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(DetailReviewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
