package com.example.nhacnho;

import java.util.Date;

public class NhacNho {
    private String theLoai;
    private String ten;
    private String chuKy;
    private String ngayBatDau;
    private String gioNhac;

    public NhacNho(String theLoai, String ten, String chuKy, String ngayBatDau, String gioNhac) {
        this.theLoai = theLoai;
        this.ten = ten;
        this.chuKy = chuKy;
        this.ngayBatDau = ngayBatDau;
        this.gioNhac = gioNhac;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChuKy() {
        return chuKy;
    }

    public void setChuKy(String chuKy) {
        this.chuKy = chuKy;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getGioNhac() {
        return gioNhac;
    }

    public void setGioNhac(String gioNhac) {
        this.gioNhac = gioNhac;
    }
}
