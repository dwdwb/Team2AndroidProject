package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.OrderCartAdapter;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.ProductBoard;
import com.example.myapplication.service.ListService;

import java.text.DecimalFormat;

public class OrderCartViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "OrderCartViewHolder";
    private CartProduct cartProduct;
    private int product_no;
    private ImageView product_image;
    private TextView product_name;
    private TextView product_price;
    private TextView product_stock;
    private TextView order_price;

    public OrderCartViewHolder(@NonNull View itemView, OrderCartAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        product_image = (ImageView) itemView.findViewById(R.id.order_product_image);
        product_name = (TextView) itemView.findViewById(R.id.order_product_name);
        product_price = (TextView) itemView.findViewById(R.id.order_product_price);
        product_stock = (TextView) itemView.findViewById(R.id.order_product_stock);
        order_price = (TextView) itemView.findViewById(R.id.order_price);
    }

    public void setData(CartProduct cartProduct) {
        int price = cartProduct.getDiscount_PRICE();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        product_name.setText(cartProduct.getProduct_NAME() + ", " + cartProduct.getProduct_OPTION());
        product_price.setText(decimalFormat.format(price)+"원");
        product_stock.setText("수량 " + cartProduct.getCart_PRODUCT_STOCK()+"개 /");
        order_price.setText((decimalFormat.format(cartProduct.getDiscount_PRICE() * cartProduct.getCart_PRODUCT_STOCK()))+"원");

        product_no = cartProduct.getProduct_NO();
        ListService.loadThumbnailImage(product_no, product_image);

    }

}
