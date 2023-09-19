package com.example.myapplication.dto;

import java.io.Serializable;

public class WriteReviewResult implements Serializable {
    private String result;
    private int review_no;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getReview_no() {
        return review_no;
    }

    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }

    @Override
    public String toString() {
        return "WriteReviewResult{" +
                "result='" + result + '\'' +
                ", review_no=" + review_no +
                '}';
    }
}
