package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.service.ListService;

import java.text.DecimalFormat;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "OrderViewHolder";
    private ProductBoard productBoard;
    private int product_no;
    private ImageView product_image;
    private TextView product_name;
    private TextView product_price;
    private TextView product_stock;
    private TextView order_price;

    public OrderViewHolder(@NonNull View itemView, OrderAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        product_image = (ImageView) itemView.findViewById(R.id.order_product_image);
        product_name = (TextView) itemView.findViewById(R.id.order_product_name);
        product_price = (TextView) itemView.findViewById(R.id.order_product_price);
        product_stock = (TextView) itemView.findViewById(R.id.order_product_stock);
        order_price = (TextView) itemView.findViewById(R.id.order_price);
    }

    public void setData(ProductBoard productBoard) {
        int price = productBoard.getDiscountPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        product_name.setText(productBoard.getProductName() + ", " + productBoard.getProductOption());
        product_price.setText(decimalFormat.format(price)+"원");
        product_stock.setText("수량 " + productBoard.getStock()+"개 /");
        order_price.setText((decimalFormat.format(productBoard.getDiscountPrice() * productBoard.getStock()))+"원");

        product_no = productBoard.getProductNo();
        ListService.loadThumbnailImage(product_no, product_image);




    }


}
