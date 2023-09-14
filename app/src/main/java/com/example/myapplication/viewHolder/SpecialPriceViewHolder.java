package com.example.myapplication.viewHolder;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;

public class SpecialPriceViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "SpecialPriceViewHolder";
    private int product_no;
    private ImageView product_image;
    private TextView product_name;
    private TextView discount_rate;
    private TextView product_price;
    private TextView discount_price;
    private RatingBar rating_bar;
    private TextView rating_score;
    private TextView rating_count;
    private LinearLayout discount_space;

    public SpecialPriceViewHolder(@NonNull View itemView) {
        super(itemView);

        //아이템 UI 얻기
        product_image = (ImageView) itemView.findViewById(R.id.product_image);
        product_name = (TextView) itemView.findViewById(R.id.product_name);
        discount_rate = (TextView) itemView.findViewById(R.id.discount_rate);
        product_price = (TextView) itemView.findViewById(R.id.product_price);
        discount_price = (TextView) itemView.findViewById(R.id.discount_price);
        rating_bar = (RatingBar) itemView.findViewById(R.id.rating_bar);
        rating_score = (TextView) itemView.findViewById(R.id.rating_score);
        rating_count = (TextView) itemView.findViewById(R.id.rating_count);

        discount_space = (LinearLayout) itemView.findViewById(R.id.discount_space);

       /* //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });*/
    }

    public void setData(MobileProductForList mobileProductForList) {
        product_no = mobileProductForList.getProduct_no();
        ListService.loadThumbnailImage(product_no, product_image);
        product_name.setText(mobileProductForList.getProduct_name());
        discount_rate.setText(mobileProductForList.getDiscount_rate());
        product_price.setText(mobileProductForList.getProduct_price());
        product_price.setPaintFlags(product_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        if (mobileProductForList.getDiscount_rate().equals("0%")) {
            discount_space.setVisibility(View.GONE);
        } else {
            discount_space.setVisibility(View.VISIBLE);
        }
        discount_price.setText(mobileProductForList.getDiscount_price());
        rating_bar.setRating((float)mobileProductForList.getStar_rate());
        rating_score.setText(String.valueOf(mobileProductForList.getStar_rate()));
        rating_count.setText("(" + String.valueOf(mobileProductForList.getRate_count()) + ")");
    }
}
