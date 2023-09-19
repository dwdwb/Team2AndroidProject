package com.example.myapplication.dto;

import java.io.Serializable;

public class Product implements Serializable {
    private int product_NO;
    private String product_NAME;
    private String product_PRICE;
    private String product_OPTION;
    private String discount_RATE;
    private String discount_PRICE;

    public int getProduct_NO() {
        return product_NO;
    }

    public void setProduct_NO(int product_NO) {
        this.product_NO = product_NO;
    }

    public String getProduct_NAME() {
        return product_NAME;
    }

    public void setProduct_NAME(String product_NAME) {
        this.product_NAME = product_NAME;
    }

    public String getProduct_PRICE() {
        return product_PRICE;
    }

    public void setProduct_PRICE(String product_PRICE) {
        this.product_PRICE = product_PRICE;
    }

    public String getProduct_OPTION() {
        return product_OPTION;
    }

    public void setProduct_OPTION(String product_OPTION) {
        this.product_OPTION = product_OPTION;
    }

    public String getDiscount_RATE() {
        return discount_RATE;
    }

    public void setDiscount_RATE(String discount_RATE) {
        this.discount_RATE = discount_RATE;
    }

    public String getDiscount_PRICE() {
        return discount_PRICE;
    }

    public void setDiscount_PRICE(String discount_PRICE) {
        this.discount_PRICE = discount_PRICE;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_NO=" + product_NO +
                ", product_NAME='" + product_NAME + '\'' +
                ", product_PRICE='" + product_PRICE + '\'' +
                ", product_OPTION='" + product_OPTION + '\'' +
                ", discount_RATE='" + discount_RATE + '\'' +
                ", discount_PRICE='" + discount_PRICE + '\'' +
                '}';
    }
}
