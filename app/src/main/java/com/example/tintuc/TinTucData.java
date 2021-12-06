package com.example.tintuc;

public class TinTucData {

    private String newsName;
    private String newsDetail;
    private Integer newsImage;

    public TinTucData(String newsName, String newsDate, Integer newwImage) {
        this.newsName = newsName;
        this.newsDetail = newsDetail;
        this.newsImage = newsImage;
    }

    public String getMovieName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getMovieDate() {
        return newsDetail;
    }

    public void setNewsDate(String newsDate) {
        this.newsDetail = newsDetail;
    }

    public Integer getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(Integer newsImage) {
        this.newsImage = newsImage;
    }
}
