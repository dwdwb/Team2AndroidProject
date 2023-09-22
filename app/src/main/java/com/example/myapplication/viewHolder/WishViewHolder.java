package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ReviewAdapter;
import com.example.myapplication.adapter.WishAdapter;
import com.example.myapplication.dto.ReviewListItem;
import com.example.myapplication.dto.WishListItem;
import com.example.myapplication.service.ListService;
import com.example.myapplication.service.ReviewService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WishViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WishViewHolder";
    private ImageView product_image;
    private TextView product_name_and_option;
    private TextView product_price;
    private Button wish_delete_btn;
    private Button wish_cart_btn;

    public WishViewHolder(@NonNull View itemView, WishAdapter.OnItemClickListener onItemClickListener1, WishAdapter.OnItemClickListener onItemClickListener2) {
        super(itemView);
        product_image = (ImageView) itemView.findViewById(R.id.product_image);
        product_name_and_option = (TextView) itemView.findViewById(R.id.product_name_and_option);
        product_price = (TextView) itemView.findViewById(R.id.product_price);
        wish_delete_btn = (Button) itemView.findViewById(R.id.wish_delete_btn);
        wish_cart_btn = (Button) itemView.findViewById(R.id.wish_cart_btn);

        wish_delete_btn.setOnClickListener(v -> {
            onItemClickListener1.onItemClick(wish_delete_btn, getAdapterPosition());
        });

        wish_cart_btn.setOnClickListener(v -> {
            onItemClickListener2.onItemClick(wish_cart_btn, getAdapterPosition());
        });
    }

    public void setData(WishListItem wishListItem) {
        ListService.loadThumbnailImage(wishListItem.getProduct_no(), product_image);
        String product_name_and_option1 = wishListItem.getProduct_name() + ", " + wishListItem.getProduct_option();
        product_name_and_option.setText(product_name_and_option1);
        product_price.setText(wishListItem.getDiscount_price());
    }

}
