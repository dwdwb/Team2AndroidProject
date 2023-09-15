package com.example.myapplication.dto;

import java.io.Serializable;
import java.util.Arrays;

public class ProductBoard implements Serializable {
    private int boardNo;				// 게시글 번호
    private int productNo;				// 상품 번호
    private String productName;			// 상품 이름
    private int productPrice;			// 상품 가격
    private String productOption;		// 상품 옵션
    private int discountRate;			// 할인 ( % , 원 )
    private int discountPrice;			// 할인 금액
    private String mediaName;			// 상품 사진 파일 이름
    private byte[] mediaData;			// 상품 사진
    private String base64Img;			// base64Img 인코딩

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductOption() {
        return productOption;
    }

    public void setProductOption(String productOption) {
        this.productOption = productOption;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public byte[] getMediaData() {
        return mediaData;
    }

    public void setMediaData(byte[] mediaData) {
        this.mediaData = mediaData;
    }

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }

    @Override
    public String toString() {
        return "ProductBoard{" +
                "boardNo=" + boardNo +
                ", productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productOption='" + productOption + '\'' +
                ", discountRate=" + discountRate +
                ", discountPrice=" + discountPrice +
                ", mediaName='" + mediaName + '\'' +
                ", mediaData=" + Arrays.toString(mediaData) +
                ", base64Img='" + base64Img + '\'' +
                '}';
    }
}
