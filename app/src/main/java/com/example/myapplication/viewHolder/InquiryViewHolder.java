package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import com.example.myapplication.adapter.InquiryAdapter;
import com.example.myapplication.dto.ProductInquiry;

import java.text.SimpleDateFormat;

public class InquiryViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "InquiryViewHolder";
    private TextView inquiry_content;
    private TextView inquiry_date;
    private LinearLayout answer_layout;
    private TextView answer_content;
    private TextView answer_date;
    private Button btn_delete_inquiry;
    public InquiryViewHolder(@NonNull View itemView, InquiryAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        inquiry_content = (TextView) itemView.findViewById(R.id.inquiry_content);
        inquiry_date = (TextView) itemView.findViewById(R.id.inquiry_date);
        answer_layout = (LinearLayout) itemView.findViewById(R.id.answer_layout);
        answer_content = (TextView) itemView.findViewById(R.id.answer_content);
        answer_date = (TextView) itemView.findViewById(R.id.answer_date);

        btn_delete_inquiry = (Button) itemView.findViewById(R.id.btn_delete_inquiry);

        //클릭 이벤트 처리
        btn_delete_inquiry.setOnClickListener(v -> {
            //Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onBtnDeleteClick(v, getAdapterPosition());
        });
    }

    public void setData(ProductInquiry productInquiry) {
        inquiry_content.setText(productInquiry.getInquiry_CONTENT());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // 원하는 날짜 형식을 지정
        String formattedInquiryDate = dateFormat.format(productInquiry.getInquiry_DATE());
        inquiry_date.setText(formattedInquiryDate);

        if(productInquiry.isEmptanswer()) {
            answer_layout.setVisibility(View.GONE);
        } else {
            answer_content.setText(productInquiry.getAnswer_CONTENT());
            String formattedAnswerDate = dateFormat.format(productInquiry.getAnswer_DATE());
            answer_date.setText(formattedAnswerDate);
        }
    }
}
