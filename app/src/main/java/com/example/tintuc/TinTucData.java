package com.example.tintuc;

public class TinTucData {

    private String newsName;
    private String newsDetail;
    private Integer newsImage;

    public TinTucData(String newsName, String newsDetail, Integer newsImage) {
        this.newsName = newsName;
        this.newsDetail = newsDetail;
        this.newsImage = newsImage;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public Integer getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(Integer newsImage) {
        this.newsImage = newsImage;
    }
}
