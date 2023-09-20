package com.example.myapplication.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartCouponAdapter;
import com.example.myapplication.dto.CartProduct;
import com.example.myapplication.dto.Coupon;
import com.example.myapplication.service.CartService;

import java.text.DecimalFormat;

public class CartCouponViewHolder  extends RecyclerView.ViewHolder {
    private static final String TAG = "CartCouponViewHolder";
    private int coupon_no;
    private TextView cart_coupon_price;
    private TextView cart_coupon_name;
    private TextView cart_coupon_rule;
    private CheckBox btn_check_coupon;
    public CartCouponViewHolder(@NonNull View itemView, CartCouponAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        cart_coupon_price = (TextView) itemView.findViewById(R.id.cart_coupon_price);
        cart_coupon_name = (TextView) itemView.findViewById(R.id.cart_coupon_name);
        cart_coupon_rule = (TextView) itemView.findViewById(R.id.cart_coupon_rule);

        btn_check_coupon = (CheckBox) itemView.findViewById(R.id.btn_check_coupon);

        //클릭 이벤트 처리
        btn_check_coupon.setOnClickListener(v -> {
            Log.i(TAG, coupon_no + " 항목이 클릭됨");
            onItemClickListener.onBtnCheckClick(btn_check_coupon, getAdapterPosition());
        });
    }

    public void setData(Coupon coupon, Boolean isChecked) {
        btn_check_coupon.setChecked(isChecked);

        coupon_no = coupon.getCoupon_NO();

        DecimalFormat df = new DecimalFormat("#,###,###");
        cart_coupon_price.setText(df.format(coupon.getDiscount_PRICE()) + coupon.getCoupon_TYPE());

        if (coupon.getCoupon_NAME() == null) {
            if(coupon.getCoupon_KIND().equals("배송비")) {
                cart_coupon_name.setText(coupon.getCoupon_KIND() + " " + df.format(coupon.getDiscount_PRICE()) + coupon.getCoupon_TYPE() + " 할인쿠폰");
            } else if(coupon.getCoupon_KIND().equals("상품")) {
                cart_coupon_name.setText(df.format(coupon.getDiscount_PRICE()) + coupon.getCoupon_TYPE() + " 할인쿠폰");
            }
        } else {
            cart_coupon_name.setText(coupon.getCoupon_NAME() + " 할인쿠폰");
        }

        if(coupon.getDiscount_RULE() == 0) {
            cart_coupon_rule.setText("금액제한없음");
        } else {
            cart_coupon_rule.setText(df.format(coupon.getDiscount_RULE()) + "원 이상 구매시");
        }
    }
}
