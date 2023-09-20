package com.example.myapplication.dto;

import java.io.Serializable;

public class WishListItem implements Serializable {
    private int product_no;
    private int shopper_no;
    private String product_name;
    private String product_option;
    private String discount_price;

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public int getShopper_no() {
        return shopper_no;
    }

    public void setShopper_no(int shopper_no) {
        this.shopper_no = shopper_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_option() {
        return product_option;
    }

    public void setProduct_option(String product_option) {
        this.product_option = product_option;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    @Override
    public String toString() {
        return "WishListItem{" +
                "product_no=" + product_no +
                ", shopper_no=" + shopper_no +
                ", product_name='" + product_name + '\'' +
                ", product_option='" + product_option + '\'' +
                ", discount_price='" + discount_price + '\'' +
                '}';
    }
}
