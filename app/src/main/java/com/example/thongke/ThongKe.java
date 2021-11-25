package com.example.thongke;

import java.io.Serializable;
import java.sql.Time;

public class ThongKe implements Serializable {
    private String InfoPercent;
    private String InfoCategory;
    private double InfoMoney;

    public ThongKe(String infoPercent, String infoCategory, Double infoMoney) {
        InfoPercent = infoPercent;
        InfoCategory = infoCategory;
        this.InfoMoney = infoMoney;
    }

    public String getInfoPercent() {
        return InfoPercent;
    }

    public void setInfoPercent(String infoPercent) {
        InfoPercent = infoPercent;
    }

    public String getInfoCategory() {
        return InfoCategory;
    }

    public void setInfoCategory(String infoCategory) {
        InfoCategory = infoCategory;
    }

    public Double getInfoMoney() {
        return InfoMoney;
    }

    public void setInfoMoney(Double infoMoney) {
        this.InfoMoney = infoMoney;
    }
}