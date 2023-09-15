package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.DetailInquiryAdapter;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductInquiry;
import com.example.myapplication.service.CartService;

public class DetailInquiryViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DetailInquiryViewHolder";
    private TextView shopper_name;
    private TextView inquiry_date;
    private TextView inquiry_content;
    private TextView answer_date;
    private TextView answer_content;

    public DetailInquiryViewHolder(@NonNull View itemView, DetailInquiryAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        shopper_name = (TextView) itemView.findViewById(R.id.shopper_name);
        inquiry_date = (TextView) itemView.findViewById(R.id.inquiry_date);
        inquiry_content = (TextView) itemView.findViewById(R.id.inquiry_content);
        answer_date = (TextView) itemView.findViewById(R.id.answer_date);
        answer_content = (TextView) itemView.findViewById(R.id.answer_content);

        //클릭 이벤트 처리
        /*itemView.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });*/
    }

    public void setData(ProductInquiry productInquiry) {
        shopper_name.setText(String.valueOf(productInquiry.getShopper_NAME()));
        inquiry_date.setText(String.valueOf(productInquiry.getInquiry_DATE()));
        inquiry_content.setText(String.valueOf(productInquiry.getInquiry_CONTENT()));
        answer_date.setText(String.valueOf(productInquiry.getAnswer_DATE()));
        answer_content.setText(String.valueOf(productInquiry.getAnswer_CONTENT()));
    }
}
