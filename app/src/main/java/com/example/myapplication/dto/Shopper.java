package com.example.myapplication.dto;

import java.io.Serializable;

public class Shopper implements Serializable {
    int shopperNo;
    String shopperId;
    String shopperPw;
    String shopperName;
    String shopperTel;
    String shopperAutoLogin;
    String activate;

    public int getShopperNo() {
        return shopperNo;
    }

    public void setShopperNo(int shopperNo) {
        this.shopperNo = shopperNo;
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

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }

    public String getShopperTel() {
        return shopperTel;
    }

    public void setShopperTel(String shopperTel) {
        this.shopperTel = shopperTel;
    }

    public String getShopperAutoLogin() {
        return shopperAutoLogin;
    }

    public void setShopperAutoLogin(String shopperAutoLogin) {
        this.shopperAutoLogin = shopperAutoLogin;
    }

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    }

    @Override
    public String toString() {
        return "Shopper{" +
                "shopperNo=" + shopperNo +
                ", shopperId='" + shopperId + '\'' +
                ", shopperPw='" + shopperPw + '\'' +
                ", shopperName='" + shopperName + '\'' +
                ", shopperTel='" + shopperTel + '\'' +
                ", shopperAutoLogin='" + shopperAutoLogin + '\'' +
                ", activate='" + activate + '\'' +
                '}';
    }
}
