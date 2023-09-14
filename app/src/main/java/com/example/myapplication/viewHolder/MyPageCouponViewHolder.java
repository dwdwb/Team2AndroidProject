package com.example.myapplication.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyPageCouponAdapter;
import com.example.myapplication.dto.Coupon;

import org.w3c.dom.Text;

public class MyPageCouponViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MyPageCouponViewHolder";

    private int coupon_no;
    private TextView coupon_price;
    private TextView coupon_name;
    private TextView coupon_rule;
    private TextView coupon_size;
    public MyPageCouponViewHolder(@NonNull View itemView, MyPageCouponAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        coupon_price = (TextView) itemView.findViewById(R.id.coupon_price);
        coupon_name = (TextView) itemView.findViewById(R.id.coupon_name);
        coupon_rule = (TextView) itemView.findViewById(R.id.coupon_rule);


    }

    public void setData(Coupon coupon) {
        coupon_no = coupon.getCoupon_NO();

        coupon_price.setText(String.valueOf(coupon.getDiscount_PRICE()) + coupon.getCoupon_TYPE());

        if (coupon.getCoupon_NAME() == null) {
            if(coupon.getCoupon_KIND().equals("배송비")) {
                coupon_name.setText(coupon.getCoupon_KIND() + " " + coupon.getDiscount_PRICE() + coupon.getCoupon_TYPE() + " 할인쿠폰");
            } else if(coupon.getCoupon_KIND().equals("상품")) {
                coupon_name.setText(coupon.getDiscount_PRICE() + coupon.getCoupon_TYPE() + " 할인쿠폰");
            }
        } else {
            coupon_name.setText(coupon.getCoupon_NAME() + " 할인쿠폰");
        }

        if(coupon.getDiscount_RULE() == 0) {
            coupon_rule.setText("금액제한없음");
        } else {
            coupon_rule.setText(coupon.getDiscount_RULE() + "원 이상 구매시");
        }
    }




}
