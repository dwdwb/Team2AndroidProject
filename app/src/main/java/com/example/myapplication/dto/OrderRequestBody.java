package com.example.myapplication.dto;

import java.io.Serializable;
import java.util.List;

public class OrderRequestBody implements Serializable {
    private Order order;
    private List<Coupon> couponList;
    private List<ReceiptHistory> receiptHistoryList;
    private List<Cart> cartList;

    public OrderRequestBody(Order order, List<Coupon> couponList, List<ReceiptHistory> receiptHistoryList, List<Cart> cartList) {
        this.order = order;
        this.couponList = couponList;
        this.receiptHistoryList = receiptHistoryList;
        this.cartList = cartList;
    }
    public OrderRequestBody() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<ReceiptHistory> getReceiptHistoryList() {
        return receiptHistoryList;
    }

    public void setReceiptHistoryList(List<ReceiptHistory> receiptHistoryList) {
        this.receiptHistoryList = receiptHistoryList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public String toString() {
        return "OrderRequestBody{" +
                "order=" + order +
                ", couponList=" + couponList +
                ", receiptHistoryList=" + receiptHistoryList +
                ", cartList=" + cartList +
                '}';
    }
}
