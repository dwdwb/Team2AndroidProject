package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.viewHolder.CartProductViewHolder;
import com.example.myapplication.viewHolder.DetailContentImageViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailContentImageAdpater extends RecyclerView.Adapter<DetailContentImageViewHolder> {
    private static final String TAG = "DetailContentImageAdpater";

    private List<Integer> mnoList = new ArrayList<>();
    @NonNull
    @Override
    public DetailContentImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.detail_product_content_image_item, parent, false);
        DetailContentImageViewHolder detailContentImageViewHolder = new DetailContentImageViewHolder(itemView);
        return detailContentImageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailContentImageViewHolder holder, int position) {
        int mno = mnoList.get(position);
        holder.setData(mno);
    }

    @Override
    public int getItemCount() {
        return mnoList.size();
    }

    public void setList(List<Integer> list) {
        this.mnoList = list;
    }

    public int getItem(int position) {
        return mnoList.get(position);
    }

}
