package com.example.myapplication.dto;

import java.io.Serializable;

public class AddressList implements Serializable {
    private int address_no;
    private int shopper_no;
    private String shipping_name;
    private String shipping_address;
    private String receiver_tel;
    private String shipping_preference;
    private String activate;

    public int getAddress_no() {
        return address_no;
    }

    public void setAddress_no(int address_no) {
        this.address_no = address_no;
    }

    public int getShopper_no() {
        return shopper_no;
    }

    public void setShopper_no(int shopper_no) {
        this.shopper_no = shopper_no;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getReceiver_tel() {
        return receiver_tel;
    }

    public void setReceiver_tel(String receiver_tel) {
        this.receiver_tel = receiver_tel;
    }

    public String getShipping_preference() {
        return shipping_preference;
    }

    public void setShipping_preference(String shipping_preference) {
        this.shipping_preference = shipping_preference;
    }

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    }

    @Override
    public String toString() {
        return "AddressList{" +
                "address_no=" + address_no +
                ", shopper_no=" + shopper_no +
                ", shipping_name='" + shipping_name + '\'' +
                ", shipping_address='" + shipping_address + '\'' +
                ", receiver_tel='" + receiver_tel + '\'' +
                ", shipping_preference='" + shipping_preference + '\'' +
                ", activate='" + activate + '\'' +
                '}';
    }
}
