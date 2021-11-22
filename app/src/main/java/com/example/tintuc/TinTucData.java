package com.example.tintuc;

public class TinTucData {

    private String newsName;
    private String newsDetail;
    private Integer newsImage;

    public TinTucData(String movieName, String movieDate, Integer movieImage) {
        this.newsName = movieName;
        this.newsDetail = newsDetail;
        this.newsImage = movieImage;
    }

    public String getMovieName() {
        return newsName;
    }

    public void setMovieName(String movieName) {
        this.newsName = movieName;
    }

    public String getMovieDate() {
        return newsDetail;
    }

    public void setMovieDate(String movieDate) {
        this.newsDetail = movieDate;
    }

    public Integer getMovieImage() {
        return newsImage;
    }

    public void setMovieImage(Integer movieImage) {
        this.newsImage = movieImage;
    }
}
