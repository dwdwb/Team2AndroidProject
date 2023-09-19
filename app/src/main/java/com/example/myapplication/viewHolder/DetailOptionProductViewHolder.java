package com.example.myapplication.viewHolder;

import android.util.Log;
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
    private TextView option_stock;
    public DetailOptionProductViewHolder(@NonNull View itemView, DetailOptionProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        option = (TextView) itemView.findViewById(R.id.option);
        option_price = (TextView) itemView.findViewById(R.id.option_price);
        //option_layout = (LinearLayout) itemView.findViewById(R.id.option_layout);

        option_stock = (TextView) itemView.findViewById(R.id.option_stock);

        //클릭 이벤트 처리
        itemView.findViewById(R.id.btn_delete_option).setOnClickListener(v -> {
            Log.i(TAG, "삭제된 product_no: " + product_no);
            onItemClickListener.onBtnDeleteClick(v, getAdapterPosition());
        });

        itemView.findViewById(R.id.btn_plus_stock).setOnClickListener(v -> {
            Log.i(TAG, "plus된 product_no: " + product_no);
            //onItemClickListener.onBtnPlusClick(v, getAdapterPosition());
            onItemClickListener.onBtnPlusClick(option_stock, option_price, getAdapterPosition());
        });

        itemView.findViewById(R.id.btn_minus_stock).setOnClickListener(v -> {
            Log.i(TAG, "minus된 product_no: " + product_no);
            onItemClickListener.onBtnMinusClick(option_stock, option_price, getAdapterPosition());
        });
    }

    public void setData(ProductBoard productBoard) {
        product_no = productBoard.getProductNo();
        option.setText(productBoard.getProductOption());
        DecimalFormat df = new DecimalFormat("#,###,###");
        option_price.setText(df.format(productBoard.getDiscountPrice()) + "원");

        //option_layout.setVisibility(View.GONE);
    }
}
