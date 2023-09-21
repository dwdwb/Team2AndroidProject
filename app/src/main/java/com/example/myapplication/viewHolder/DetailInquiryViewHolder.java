package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.DetailInquiryAdapter;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.service.CartService;

import java.text.SimpleDateFormat;

public class DetailInquiryViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DetailInquiryViewHolder";
    private TextView shopper_name;
    private TextView inquiry_date;
    private TextView inquiry_content;
    private TextView answer_date;
    private TextView answer_content;
    private LinearLayout answer_layout;

    public DetailInquiryViewHolder(@NonNull View itemView, DetailInquiryAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        shopper_name = (TextView) itemView.findViewById(R.id.shopper_name);
        inquiry_date = (TextView) itemView.findViewById(R.id.inquiry_date);
        inquiry_content = (TextView) itemView.findViewById(R.id.inquiry_content);
        answer_date = (TextView) itemView.findViewById(R.id.answer_date);
        answer_content = (TextView) itemView.findViewById(R.id.answer_content);

        answer_layout = (LinearLayout) itemView.findViewById(R.id.answer_layout);

        //클릭 이벤트 처리
        /*itemView.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });*/
    }

    public void setData(ProductInquiry productInquiry) {
        shopper_name.setText(productInquiry.getShopper_NAME());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // 원하는 날짜 형식을 지정
        String formattedInquiryDate = dateFormat.format(productInquiry.getInquiry_DATE());
        inquiry_date.setText(formattedInquiryDate);
        inquiry_content.setText(productInquiry.getInquiry_CONTENT());
        if(productInquiry.isEmptanswer()) {
            answer_layout.setVisibility(View.GONE);
        } else {
            String formattedAnswerDate = dateFormat.format(productInquiry.getAnswer_DATE());
            answer_date.setText(formattedAnswerDate);
            answer_content.setText(productInquiry.getAnswer_CONTENT());
        }
    }
}
