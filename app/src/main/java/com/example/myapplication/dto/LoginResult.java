package com.example.myapplication.dto;

import java.io.Serializable;

public class LoginResult implements Serializable {
    private String result;
    private String shopperId;
    private String shopperPw;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getShopperPw() {
        return shopperPw;
    }

    public void setShopperPw(String shopperPw) {
        this.shopperPw = shopperPw;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "result='" + result + '\'' +
                ", shopperId='" + shopperId + '\'' +
                ", shopperPw='" + shopperPw + '\'' +
                '}';
    }
}
