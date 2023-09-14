package com.example.myapplication.dto;

import java.io.Serializable;

public class Coupon implements Serializable {
    private int coupon_NO;		//쿠폰 번호
    private int shopper_NO;		//회원 번호
    private String coupon_NAME;	//쿠폰 이름
    private String coupon_TYPE;	//쿠폰 분류(원 or %)
    private String coupon_KIND;	//쿠폰 종류(배송비 or 상품)
    private int discount_PRICE;	//쿠폰 할인금액
    private int discount_RULE;	//쿠폰 적용 조건 금액
    private boolean used;		//쿠폰 사용여부

    public int getCoupon_NO() {
        return coupon_NO;
    }

    public void setCoupon_NO(int coupon_NO) {
        this.coupon_NO = coupon_NO;
    }

    public int getShopper_NO() {
        return shopper_NO;
    }

    public void setShopper_NO(int shopper_NO) {
        this.shopper_NO = shopper_NO;
    }

    public String getCoupon_NAME() {
        return coupon_NAME;
    }

    public void setCoupon_NAME(String coupon_NAME) {
        this.coupon_NAME = coupon_NAME;
    }

    public String getCoupon_TYPE() {
        return coupon_TYPE;
    }

    public void setCoupon_TYPE(String coupon_TYPE) {
        this.coupon_TYPE = coupon_TYPE;
    }

    public String getCoupon_KIND() {
        return coupon_KIND;
    }

    public void setCoupon_KIND(String coupon_KIND) {
        this.coupon_KIND = coupon_KIND;
    }

    public int getDiscount_PRICE() {
        return discount_PRICE;
    }

    public void setDiscount_PRICE(int discount_PRICE) {
        this.discount_PRICE = discount_PRICE;
    }

    public int getDiscount_RULE() {
        return discount_RULE;
    }

    public void setDiscount_RULE(int discount_RULE) {
        this.discount_RULE = discount_RULE;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "coupon_NO=" + coupon_NO +
                ", shopper_NO=" + shopper_NO +
                ", coupon_NAME='" + coupon_NAME + '\'' +
                ", coupon_TYPE='" + coupon_TYPE + '\'' +
                ", coupon_KIND='" + coupon_KIND + '\'' +
                ", discount_PRICE=" + discount_PRICE +
                ", discount_RULE=" + discount_RULE +
                ", used=" + used +
                '}';
    }
}
