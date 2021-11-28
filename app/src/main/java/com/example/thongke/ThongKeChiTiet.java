package com.example.thongke;

import java.io.Serializable;
import java.time.LocalDate;

public class ThongKeChiTiet implements Serializable {
    private String InfoTheLoai;
    private String InfoTaiKhoan;
    private double InfoCTMoney;
    private LocalDate InfoTime;

    public ThongKeChiTiet(String infoTheLoai, String infoTaiKhoan, double infoCTMoney, LocalDate infoTime) {
        this.InfoTheLoai = infoTheLoai;
        this.InfoTaiKhoan = infoTaiKhoan;
        this.InfoCTMoney = infoCTMoney;
        this.InfoTime = infoTime;
    }

    public String getInfoTheLoai() {
        return InfoTheLoai;
    }

    public void setInfoTheLoai(String infoTheLoai) {
        this.InfoTheLoai = infoTheLoai;
    }

    public String getInfoTaiKhoan() {
        return InfoTaiKhoan;
    }

    public void setInfoTaiKhoan(String infoTaiKhoan) {
        this.InfoTaiKhoan = infoTaiKhoan;
    }

    public double getInfoCTMoney() {
        return InfoCTMoney;
    }

    public void setInfoCTMoney(double infoCTMoney) {
        this.InfoCTMoney = infoCTMoney;
    }

    public LocalDate getInfoTime() {
        return InfoTime;
    }

    public void setInfoTime(LocalDate infoTime) {
        this.InfoTime = infoTime;
    }
}
