package com.example.myapplication.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartProductAdapter;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.service.CartService;
import com.example.myapplication.service.ListService;

public class CartProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "CartProductViewHolder";
    private int product_no;
    private ImageView cart_product_image;
    private TextView cart_product_name_option;
    private TextView cart_product_price;
    private TextView cart_product_stock;

    public CartProductViewHolder(@NonNull View itemView, CartProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        cart_product_image = (ImageView) itemView.findViewById(R.id.cart_product_image);
        cart_product_name_option = (TextView) itemView.findViewById(R.id.cart_product_name_option);
        cart_product_price = (TextView) itemView.findViewById(R.id.cart_product_price);
        cart_product_stock = (TextView) itemView.findViewById(R.id.cart_product_stock);

        //클릭 이벤트 처리
        /*itemView.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });*/
    }

    public void setData(CartProduct cartProduct) {
        product_no = cartProduct.getProduct_NO();
        CartService.loadCartProductImage(product_no, cart_product_image);
        cart_product_name_option.setText(cartProduct.getProduct_NAME() + ", " + cartProduct.getProduct_OPTION());
        cart_product_price.setText(String.valueOf(cartProduct.getDiscount_PRICE()));
        cart_product_stock.setText(String.valueOf(cartProduct.getCart_PRODUCT_STOCK()));
    }
}
