package com.example.myapplication.dto;

import java.io.Serializable;

public class CartProduct implements Serializable {
    private int product_NO;			//상품 번호
    private int cart_PRODUCT_STOCK;	//상품 수량
    private int product_STOCK;		//상품 재고
    private String product_NAME;	//상품 이름
    private String product_OPTION;	//상품 옵션
    private int discount_PRICE;		//상품 가격

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

    public int getProduct_STOCK() {
        return product_STOCK;
    }

    public void setProduct_STOCK(int product_STOCK) {
        this.product_STOCK = product_STOCK;
    }

    public String getProduct_NAME() {
        return product_NAME;
    }

    public void setProduct_NAME(String product_NAME) {
        this.product_NAME = product_NAME;
    }

    public String getProduct_OPTION() {
        return product_OPTION;
    }

    public void setProduct_OPTION(String product_OPTION) {
        this.product_OPTION = product_OPTION;
    }

    public int getDiscount_PRICE() {
        return discount_PRICE;
    }

    public void setDiscount_PRICE(int discount_PRICE) {
        this.discount_PRICE = discount_PRICE;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "product_NO=" + product_NO +
                ", cart_PRODUCT_STOCK=" + cart_PRODUCT_STOCK +
                ", product_STOCK=" + product_STOCK +
                ", product_NAME='" + product_NAME + '\'' +
                ", product_OPTION='" + product_OPTION + '\'' +
                ", discount_PRICE=" + discount_PRICE +
                '}';
    }
}
