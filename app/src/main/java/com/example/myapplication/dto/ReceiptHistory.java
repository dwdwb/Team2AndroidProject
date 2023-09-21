package com.example.myapplication.dto;

import java.io.Serializable;

public class ReceiptHistory implements Serializable {

    private int product_NO;	//상품 번호
    private int price;		//상품 가격
    private int stock;		//상품 수량

    public int getProduct_NO() {
        return product_NO;
    }

    public void setProduct_NO(int product_NO) {
        this.product_NO = product_NO;
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

    @Override
    public String toString() {
        return "ReceiptHistory{" +
                "product_NO=" + product_NO +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
