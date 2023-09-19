package com.example.myapplication.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPageOrderedViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MyPageOrderedViewHolder";

    private TextView order_no_text_view;
    private TextView product_no_text_view;
    private int product_no;
    private ImageView ordered_image;

    private TextView order_date;
    private TextView product_name;
    private TextView product_option;
    private TextView price;
    private TextView stock;
    private TextView payment_price;

    private Button btnReview;
    public MyPageOrderedViewHolder(@NonNull View itemView, MyPageOrderedAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        order_no_text_view = (TextView) itemView.findViewById(R.id.order_no_text_view);
        product_no_text_view = (TextView) itemView.findViewById(R.id.product_no_text_view);
        ordered_image = (ImageView) itemView.findViewById(R.id.ordered_image);
        order_date = (TextView) itemView.findViewById(R.id.order_date);
        product_name = (TextView) itemView.findViewById(R.id.ordered_product_name);
        price = (TextView) itemView.findViewById(R.id.price);
        payment_price = (TextView) itemView.findViewById(R.id.payment_price);
        btnReview = itemView.findViewById(R.id.btn_write_review);


        btnReview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 해당 아이템의 product_no를 전달
                Log.i(TAG, "product_no 선택됨?"+product_no);

                onItemClickListener.onItemClick(btnReview, getAdapterPosition());

            }
        });

    }




    public void setData(OrderHistory orderHistory) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 날짜 형식을 지정
        String formattedDate = dateFormat.format(orderHistory.getOrder_date());
        order_date.setText(formattedDate);

        product_no = orderHistory.getProduct_no();
        ListService.loadThumbnailImage(product_no, ordered_image);

        order_no_text_view.setText(String.valueOf(orderHistory.getOrder_no()));
        product_no_text_view.setText(String.valueOf(orderHistory.getProduct_no()));
        product_name.setText(orderHistory.getProduct_name() + orderHistory.getProduct_option());
        price.setText(orderHistory.getStock()+"개 , "+orderHistory.getPrice() + "원");
        payment_price.setText("총 결제 금액: " + orderHistory.getPayment_price()+"원");

    }

}
