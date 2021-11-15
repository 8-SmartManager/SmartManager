package com.example.nhacnho;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class NhacNho implements Serializable {
    private String theLoai;
    private String ten;
    private String chuKy;
    private LocalDate ngayBatDau;
    private Time gioNhac;

    public NhacNho(String theLoai, String ten, String chuKy, LocalDate ngayBatDau,Time gioNhac) {
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

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Time getGioNhac() {
        return gioNhac;
    }

    public void setGioNhac(Time gioNhac) {
        this.gioNhac = gioNhac;
    }
}
