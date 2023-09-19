package com.example.myapplication.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
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

import java.text.DecimalFormat;

public class CartProductViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "CartProductViewHolder";
    private int product_no;
    private ImageView cart_product_image;
    private TextView cart_product_name_option;
    private TextView cart_product_price;
    private TextView cart_product_stock;
    private CheckBox btn_check_cart;
    private ImageButton btn_delete_cart_product;
    private ImageButton btn_plus_cart_stock;
    private ImageButton btn_minus_cart_stock;

    public CartProductViewHolder(@NonNull View itemView, CartProductAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        cart_product_image = (ImageView) itemView.findViewById(R.id.cart_product_image);
        cart_product_name_option = (TextView) itemView.findViewById(R.id.cart_product_name_option);
        cart_product_price = (TextView) itemView.findViewById(R.id.cart_product_price);
        cart_product_stock = (TextView) itemView.findViewById(R.id.cart_product_stock);

        btn_check_cart = (CheckBox) itemView.findViewById(R.id.btn_check_cart);
        btn_delete_cart_product = (ImageButton) itemView.findViewById(R.id.btn_delete_cart_product);
        btn_plus_cart_stock = (ImageButton) itemView.findViewById(R.id.btn_plus_cart_stock);
        btn_minus_cart_stock = (ImageButton) itemView.findViewById(R.id.btn_minus_cart_stock);

        //클릭 이벤트 처리
        btn_check_cart.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onBtnCheckClick(btn_check_cart, getAdapterPosition());
        });
        btn_delete_cart_product.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onBtnDeleteClick(v, getAdapterPosition());
        });
        btn_plus_cart_stock.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onBtnPlusClick(cart_product_stock, cart_product_price, getAdapterPosition());
        });
        btn_minus_cart_stock.setOnClickListener(v -> {
            Log.i(TAG, product_no + " 항목이 클릭됨");
            onItemClickListener.onBtnMinusClick(cart_product_stock, cart_product_price, getAdapterPosition());
        });
    }

    public void setData(CartProduct cartProduct, Boolean isChecked) {
        btn_check_cart.setChecked(isChecked);

        product_no = cartProduct.getProduct_NO();
        CartService.loadCartProductImage(product_no, cart_product_image);
        cart_product_name_option.setText(cartProduct.getProduct_NAME() + ", " + cartProduct.getProduct_OPTION());
        cart_product_stock.setText(String.valueOf(cartProduct.getCart_PRODUCT_STOCK()));
        DecimalFormat df = new DecimalFormat("#,###,###");
        cart_product_price.setText(df.format(cartProduct.getCart_PRODUCT_STOCK() * cartProduct.getDiscount_PRICE()) + "원");
    }
}
