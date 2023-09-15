package com.example.myapplication.dto;

import java.io.Serializable;

public class ReviewInfo implements Serializable {
    private int starRateAvg;				// 리뷰 평균 점수
    private float totalReviewScore;			// 리뷰 총 점수
    private int reviewCount;

    public int getStarRateAvg() {
        return starRateAvg;
    }

    public void setStarRateAvg(int starRateAvg) {
        this.starRateAvg = starRateAvg;
    }

    public float getTotalReviewScore() {
        return totalReviewScore;
    }

    public void setTotalReviewScore(float totalReviewScore) {
        this.totalReviewScore = totalReviewScore;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public String toString() {
        return "ReviewInfo{" +
                "starRateAvg=" + starRateAvg +
                ", totalReviewScore=" + totalReviewScore +
                '}';
    }
}
