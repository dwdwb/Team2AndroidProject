package com.example.myapplication.dto;

import java.io.Serializable;

public class Cart implements Serializable {

    private int shopper_NO;			//회원번호
    private int product_NO;			//상품번호
    private int cart_PRODUCT_STOCK;	//장바구니 상품수량

    public int getShopper_NO() {
        return shopper_NO;
    }

    public void setShopper_NO(int shopper_NO) {
        this.shopper_NO = shopper_NO;
    }

    public int getProduct_NO() {
        return product_NO;
    }

    public void setProduct_NO(int product_NO) {
        this.product_NO = product_NO;
    }

    public int getCart_PRODUCT_STOCK() {
        return cart_PRODUCT_STOCK;
    }

    public void setCart_PRODUCT_STOCK(int cart_PRODUCT_STOCK) {
        this.cart_PRODUCT_STOCK = cart_PRODUCT_STOCK;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "shopper_NO=" + shopper_NO +
                ", product_NO=" + product_NO +
                ", cart_PRODUCT_STOCK=" + cart_PRODUCT_STOCK +
                '}';
    }
}
