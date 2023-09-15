package com.example.myapplication.dto;

import java.io.Serializable;

public class ProductInquiry implements Serializable {
    private int inquiry_NO;			//상품문의 번호
    private int board_NO;			//상품게시글 번호
    private String product_NAME;	//상품이름
    private String inquiry_CONTENT;	//문의내용
    private long inquiry_DATE;		//문의날짜
    private String strInquiryDate;	//문의날짜(날짜 포맷팅 후 저장할 필드)
    private boolean emptanswer;		//답변여부(default true)
    private String answer_CONTENT;	//답변내용
    private long answer_DATE;		//답변날짜
    private String strAnswerDate;	//답변날짜(날짜 포맷팅 후 저장할 필드)
    private String shopper_NAME;	//문의한 회원이름(이*지)

    public int getInquiry_NO() {
        return inquiry_NO;
    }

    public void setInquiry_NO(int inquiry_NO) {
        this.inquiry_NO = inquiry_NO;
    }

    public int getBoard_NO() {
        return board_NO;
    }

    public void setBoard_NO(int board_NO) {
        this.board_NO = board_NO;
    }

    public String getProduct_NAME() {
        return product_NAME;
    }

    public void setProduct_NAME(String product_NAME) {
        this.product_NAME = product_NAME;
    }

    public String getInquiry_CONTENT() {
        return inquiry_CONTENT;
    }

    public void setInquiry_CONTENT(String inquiry_CONTENT) {
        this.inquiry_CONTENT = inquiry_CONTENT;
    }

    public long getInquiry_DATE() {
        return inquiry_DATE;
    }

    public void setInquiry_DATE(long inquiry_DATE) {
        this.inquiry_DATE = inquiry_DATE;
    }

    public String getStrInquiryDate() {
        return strInquiryDate;
    }

    public void setStrInquiryDate(String strInquiryDate) {
        this.strInquiryDate = strInquiryDate;
    }

    public boolean isEmptanswer() {
        return emptanswer;
    }

    public void setEmptanswer(boolean emptanswer) {
        this.emptanswer = emptanswer;
    }

    public String getAnswer_CONTENT() {
        return answer_CONTENT;
    }

    public void setAnswer_CONTENT(String answer_CONTENT) {
        this.answer_CONTENT = answer_CONTENT;
    }

    public long getAnswer_DATE() {
        return answer_DATE;
    }

    public void setAnswer_DATE(long answer_DATE) {
        this.answer_DATE = answer_DATE;
    }

    public String getStrAnswerDate() {
        return strAnswerDate;
    }

    public void setStrAnswerDate(String strAnswerDate) {
        this.strAnswerDate = strAnswerDate;
    }

    public String getShopper_NAME() {
        return shopper_NAME;
    }

    public void setShopper_NAME(String shopper_NAME) {
        this.shopper_NAME = shopper_NAME;
    }

    @Override
    public String toString() {
        return "ProductInquiry{" +
                "inquiry_NO=" + inquiry_NO +
                ", board_NO=" + board_NO +
                ", product_NAME='" + product_NAME + '\'' +
                ", inquiry_CONTENT='" + inquiry_CONTENT + '\'' +
                ", inquiry_DATE=" + inquiry_DATE +
                ", strInquiryDate='" + strInquiryDate + '\'' +
                ", emptanswer=" + emptanswer +
                ", answer_CONTENT='" + answer_CONTENT + '\'' +
                ", answer_DATE=" + answer_DATE +
                ", strAnswerDate='" + strAnswerDate + '\'' +
                ", shopper_NAME='" + shopper_NAME + '\'' +
                '}';
    }
}
