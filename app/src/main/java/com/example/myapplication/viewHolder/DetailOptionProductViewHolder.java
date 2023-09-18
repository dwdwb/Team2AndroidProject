package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DetailOptionProductAdapter;
import com.example.myapplication.dto.ProductBoard;

import java.text.DecimalFormat;

public class DetailOptionProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DetailOptionProductAdapter";
    private int product_no;
    private TextView option;
    private TextView option_price;
    private LinearLayout option_layout;
    public DetailOptionProductViewHolder(@NonNull View itemView, DetailOptionProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        option = (TextView) itemView.findViewById(R.id.option);
        option_price = (TextView) itemView.findViewById(R.id.option_price);
        option_layout = (LinearLayout) itemView.findViewById(R.id.option_layout);

        //클릭 이벤트 처리
        /*itemView.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });*/
    }

    public void setData(ProductBoard productBoard) {
        product_no = productBoard.getProductNo();
        option.setText(productBoard.getProductOption());
        DecimalFormat df = new DecimalFormat("#,###,###");
        option_price.setText(df.format(productBoard.getDiscountPrice()));

        //option_layout.setVisibility(View.GONE);
    }
}
