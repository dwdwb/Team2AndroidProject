package com.example.myapplication.dto;

import java.io.Serializable;

public class Order implements Serializable {


    private int shopper_NO; 				//유저 번호
    private int address_NO;					//배송지 번호
    //private long ORDER_DATE;
    private int total_PRICE;				//총 상품가격
    private int discount_PRICE;				//할인금액
    private int shipping_PRICE;				//배송비
    private int payment_PRICE;				//결제금액
    private String payment_TYPE;			//결제 유형(계좌이체, 신용/체크카드, 휴대폰, 무통장입금(가상계좌))
    private String cash_RECEIPT_PURPOSE;	//현금영수증 발행목적(소득공제, 지출증빙)
    private String cash_RECEIPT_TYPE;		//현금영수증 종류(휴대폰번호, 현금영수증카드)
    private String cash_RECEIPT_NO;			//현금영수증 번호

    public int getShopper_NO() {
        return shopper_NO;
    }

    public void setShopper_NO(int shopper_NO) {
        this.shopper_NO = shopper_NO;
    }

    public int getAddress_NO() {
        return address_NO;
    }

    public void setAddress_NO(int address_NO) {
        this.address_NO = address_NO;
    }

    public int getTotal_PRICE() {
        return total_PRICE;
    }

    public void setTotal_PRICE(int total_PRICE) {
        this.total_PRICE = total_PRICE;
    }

    public int getDiscount_PRICE() {
        return discount_PRICE;
    }

    public void setDiscount_PRICE(int discount_PRICE) {
        this.discount_PRICE = discount_PRICE;
    }

    public int getShipping_PRICE() {
        return shipping_PRICE;
    }

    public void setShipping_PRICE(int shipping_PRICE) {
        this.shipping_PRICE = shipping_PRICE;
    }

    public int getPayment_PRICE() {
        return payment_PRICE;
    }

    public void setPayment_PRICE(int payment_PRICE) {
        this.payment_PRICE = payment_PRICE;
    }

    public String getPayment_TYPE() {
        return payment_TYPE;
    }

    public void setPayment_TYPE(String payment_TYPE) {
        this.payment_TYPE = payment_TYPE;
    }

    public String getCash_RECEIPT_PURPOSE() {
        return cash_RECEIPT_PURPOSE;
    }

    public void setCash_RECEIPT_PURPOSE(String cash_RECEIPT_PURPOSE) {
        this.cash_RECEIPT_PURPOSE = cash_RECEIPT_PURPOSE;
    }

    public String getCash_RECEIPT_TYPE() {
        return cash_RECEIPT_TYPE;
    }

    public void setCash_RECEIPT_TYPE(String cash_RECEIPT_TYPE) {
        this.cash_RECEIPT_TYPE = cash_RECEIPT_TYPE;
    }

    public String getCash_RECEIPT_NO() {
        return cash_RECEIPT_NO;
    }

    public void setCash_RECEIPT_NO(String cash_RECEIPT_NO) {
        this.cash_RECEIPT_NO = cash_RECEIPT_NO;
    }

    @Override
    public String toString() {
        return "Order{" +
                "shopper_NO=" + shopper_NO +
                ", address_NO=" + address_NO +
                ", total_PRICE=" + total_PRICE +
                ", discount_PRICE=" + discount_PRICE +
                ", shipping_PRICE=" + shipping_PRICE +
                ", payment_PRICE=" + payment_PRICE +
                ", payment_TYPE='" + payment_TYPE + '\'' +
                ", cash_RECEIPT_PURPOSE='" + cash_RECEIPT_PURPOSE + '\'' +
                ", cash_RECEIPT_TYPE='" + cash_RECEIPT_TYPE + '\'' +
                ", cash_RECEIPT_NO='" + cash_RECEIPT_NO + '\'' +
                '}';
    }
}
