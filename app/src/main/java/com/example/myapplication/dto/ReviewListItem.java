package com.example.myapplication.dto;

import java.io.Serializable;
import java.util.Date;

public class ReviewListItem implements Serializable {
    private int review_no;
    private int product_no;
    private long write_date;
    private String product_name;
    private String product_option;
    private String content;

    public int getReview_no() {
        return review_no;
    }

    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public long getWrite_date() {
        return write_date;
    }

    public void setWrite_date(long write_date) {
        this.write_date = write_date;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReviewListItem{" +
                "review_no=" + review_no +
                ", product_no=" + product_no +
                ", write_date=" + write_date +
                ", product_name='" + product_name + '\'' +
                ", product_option='" + product_option + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
