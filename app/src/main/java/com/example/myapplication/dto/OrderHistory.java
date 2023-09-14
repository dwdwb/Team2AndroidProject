package com.example.myapplication.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderHistory implements Serializable  {
    private long order_date;

    private int product_no;
    private String product_name;
    private String product_option;
    private int price;
    private int stock;
    private int payment_price;
    public long getOrder_date() {
        return order_date;
    }

    public void setOrder_date(long order_date) {
        this.order_date = order_date;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPayment_price() {
        return payment_price;
    }

    public void setPayment_price(int payment_price) {
        this.payment_price = payment_price;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "order_date=" + order_date +
                ", product_no=" + product_no +
                ", product_name='" + product_name + '\'' +
                ", product_option='" + product_option + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", payment_price=" + payment_price +
                '}';
    }
}
