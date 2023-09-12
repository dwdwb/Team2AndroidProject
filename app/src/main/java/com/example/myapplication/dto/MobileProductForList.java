package com.example.myapplication.dto;

import java.io.Serializable;

public class MobileProductForList implements Serializable {
    private int product_no;
    private String product_name;
    private String product_price;
    private String discount_rate;
    private String discount_price;
    private double star_rate;
    private int rate_count;

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(String discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public double getStar_rate() {
        return star_rate;
    }

    public void setStar_rate(double star_rate) {
        this.star_rate = star_rate;
    }

    public int getRate_count() {
        return rate_count;
    }

    public void setRate_count(int rate_count) {
        this.rate_count = rate_count;
    }

    @Override
    public String toString() {
        return "MobileProductList{" +
                "product_no=" + product_no +
                ", product_name='" + product_name + '\'' +
                ", product_price='" + product_price + '\'' +
                ", discount_rate='" + discount_rate + '\'' +
                ", discount_price='" + discount_price + '\'' +
                ", star_rate=" + star_rate +
                ", rate_count=" + rate_count +
                '}';
    }


}
