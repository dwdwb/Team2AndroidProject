package com.example.myapplication.dto;

import java.io.Serializable;

public class Shopper implements Serializable {
    int shopper_no;
    String shopper_id;
    String shopper_pw;
    String shopper_name;
    String shopper_tel;
    String shopper_auto_login;
    String activate;

    public int getShopper_no() {
        return shopper_no;
    }

    public void setShopper_no(int shopper_no) {
        this.shopper_no = shopper_no;
    }

    public String getShopper_id() {
        return shopper_id;
    }

    public void setShopper_id(String shopper_id) {
        this.shopper_id = shopper_id;
    }

    public String getShopper_pw() {
        return shopper_pw;
    }

    public void setShopper_pw(String shopper_pw) {
        this.shopper_pw = shopper_pw;
    }

    public String getShopper_name() {
        return shopper_name;
    }

    public void setShopper_name(String shopper_name) {
        this.shopper_name = shopper_name;
    }

    public String getShopper_tel() {
        return shopper_tel;
    }

    public void setShopper_tel(String shopper_tel) {
        this.shopper_tel = shopper_tel;
    }

    public String getShopper_auto_login() {
        return shopper_auto_login;
    }

    public void setShopper_auto_login(String shopper_auto_login) {
        this.shopper_auto_login = shopper_auto_login;
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
                "shopper_no=" + shopper_no +
                ", shopper_id='" + shopper_id + '\'' +
                ", shopper_pw='" + shopper_pw + '\'' +
                ", shopper_name='" + shopper_name + '\'' +
                ", shopper_tel='" + shopper_tel + '\'' +
                ", shopper_auto_login='" + shopper_auto_login + '\'' +
                ", activate='" + activate + '\'' +
                '}';
    }
}
