package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyPageCouponAdapter;
import com.example.myapplication.adapter.MyPageOrderedAdapter;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.dto.OrderHistory;
import com.example.myapplication.dto.MobileProductForList;
import com.example.myapplication.service.ListService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPageOrderedViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MyPageOrderedViewHolder";

    private int product_no;
    private ImageView ordered_image;

    private TextView order_date;
    private TextView product_name;
    private TextView product_option;
    private TextView price;
    private TextView stock;
    private TextView payment_price;
    public MyPageOrderedViewHolder(@NonNull View itemView, MyPageOrderedAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        ordered_image = (ImageView) itemView.findViewById(R.id.ordered_image);
        order_date = (TextView) itemView.findViewById(R.id.order_date);
        product_name = (TextView) itemView.findViewById(R.id.ordered_product_name);
        price = (TextView) itemView.findViewById(R.id.price);
        payment_price = (TextView) itemView.findViewById(R.id.payment_price);
    }

    public void setData(OrderHistory orderHistory) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 날짜 형식을 지정
        String formattedDate = dateFormat.format(orderHistory.getOrder_date());
        order_date.setText(formattedDate);

        product_no = orderHistory.getProduct_no();
        ListService.loadThumbnailImage(product_no, ordered_image);

        product_name.setText(orderHistory.getProduct_name() + orderHistory.getProduct_option());
        price.setText(orderHistory.getStock()+" , "+orderHistory.getPrice());
        payment_price.setText(String.valueOf(orderHistory.getPayment_price()));

    }

}
