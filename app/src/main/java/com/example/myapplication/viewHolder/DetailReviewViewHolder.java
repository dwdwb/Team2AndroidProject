package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DetailInquiryAdapter;
import com.example.myapplication.adapter.DetailReviewAdapter;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.dto.Review;

import java.text.SimpleDateFormat;

public class DetailReviewViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DetailReviewViewHolder";
    private TextView shopper_name;
    private RatingBar rating;
    private TextView review_date;
    private TextView review_content;
    private TextView review_help_count;

    public DetailReviewViewHolder(@NonNull View itemView, DetailReviewAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        shopper_name = (TextView) itemView.findViewById(R.id.shopper_name);
        rating = (RatingBar) itemView.findViewById(R.id.rating);
        review_date = (TextView) itemView.findViewById(R.id.review_date);
        review_content = (TextView) itemView.findViewById(R.id.review_content);
        review_help_count = (TextView) itemView.findViewById(R.id.review_help_count);

        //클릭 이벤트 처리
        /*itemView.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });*/
    }

    public void setData(Review review) {
        shopper_name.setText(String.valueOf(review.getShopper_name()));
        rating.setRating(review.getStar_rate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 날짜 형식을 지정
        String formattedDate = dateFormat.format(review.getWrite_date());
        review_date.setText(formattedDate);
        review_content.setText(String.valueOf(review.getContent()));
        review_help_count.setText(String.valueOf(review.getHelp_point()));
    }
}
