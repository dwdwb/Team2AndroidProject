package com.example.myapplication.dto;

public class AddressResult {
    private String address_result;
    private int address_no;
    private String message;

    public String getAddress_result() {
        return address_result;
    }

    public void setAddress_result(String address_result) {
        this.address_result = address_result;
    }

    public int getAddress_no() {
        return address_no;
    }

    public void setAddress_no(int address_no) {
        this.address_no = address_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AddressResult{" +
                "address_result='" + address_result + '\'' +
                ", address_no=" + address_no +
                ", message='" + message + '\'' +
                '}';
    }
}
