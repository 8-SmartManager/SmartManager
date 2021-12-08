package com.example.taikhoan;

import java.io.Serializable;

public class TaiKhoanActivity implements Serializable {
    private String InfoTaiKhoan;
    private double InfoSoTien;

    public TaiKhoanActivity(String infoTaiKhoan, double infoSoTien) {
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
