package com.example.thongke;

import java.io.Serializable;
import java.sql.Time;

public class ThongKe implements Serializable {
    private double InfoPercent;
    private String InfoCategory;
    private String InfoThuOrChi;
    private double InfoMoney;

    public ThongKe(double infoPercent, String infoCategory, String infoThuOrChi, double infoMoney) {
        InfoPercent = infoPercent;
        InfoCategory = infoCategory;
        InfoThuOrChi = infoThuOrChi;
        InfoMoney = infoMoney;
    }

    public double getInfoPercent() {
        return InfoPercent;
    }

    public void setInfoPercent(double infoPercent) {
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

    public String getInfoThuOrChi() {
        return InfoThuOrChi;
    }

    public void setInfoThuOrChi(String infoThuOrChi) {
        InfoThuOrChi = infoThuOrChi;
    }

    public void setInfoMoney(double infoMoney) {
        InfoMoney = infoMoney;
    }
}