package com.example.myapplication.dto;

import java.io.Serializable;

public class Review implements Serializable {
    private int review_no;				// 리뷰 고유번호
    private int order_no;				// 결제 고유번호
    private int product_no;				// 상품 고유 번호
    private int board_no;				// 게시글 고유번호
    private int star_rate;				// 평점 ( 0 ~ 100 )
    private int help_point;				// 평점 ( 0 ~ 100 )
    private String content;				// 리뷰 내용
    private String shopper_name;			// 리뷰 작성자
    private String product_name;			// 리뷰 작성자
    private long write_date;				// 리뷰 작성일

    public int getReview_no() {
        return review_no;
    }

    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public int getStar_rate() {
        return star_rate;
    }

    public void setStar_rate(int star_rate) {
        this.star_rate = star_rate;
    }

    public int getHelp_point() {
        return help_point;
    }

    public void setHelp_point(int help_point) {
        this.help_point = help_point;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShopper_name() {
        return shopper_name;
    }

    public void setShopper_name(String shopper_name) {
        this.shopper_name = shopper_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public long getWrite_date() {
        return write_date;
    }

    public void setWrite_date(long write_date) {
        this.write_date = write_date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_no=" + review_no +
                ", order_no=" + order_no +
                ", product_no=" + product_no +
                ", board_no=" + board_no +
                ", star_rate=" + star_rate +
                ", help_point=" + help_point +
                ", content='" + content + '\'' +
                ", shopper_name='" + shopper_name + '\'' +
                ", product_name='" + product_name + '\'' +
                ", write_date=" + write_date +
                '}';
    }
}
