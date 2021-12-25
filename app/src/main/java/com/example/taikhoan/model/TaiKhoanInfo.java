package com.example.taikhoan.model;

import java.io.Serializable;

public class TaiKhoanInfo implements Serializable {
    private String InfoTaiKhoan;
    private double InfoSoTien;

    public TaiKhoanInfo(String infoTaiKhoan, double infoSoTien) {
        InfoTaiKhoan = infoTaiKhoan;
        InfoSoTien = infoSoTien;
    }
    public String getInfoTaiKhoan() {
        return InfoTaiKhoan;
    }

    public void setInfoTaiKhoan(String infoTaiKhoan) {
        InfoTaiKhoan = infoTaiKhoan;
    }
    public double getInfoSoTien() {
        return InfoSoTien;
    }

    public void setInfoSoTien(double infoSoTien) {
        InfoSoTien = infoSoTien;
    }
}
