package com.example.model;

import java.io.Serializable;

public class ThemTaiKhoanActivity implements Serializable {
    private  String tenTK;
    private String soTien;

    public ThemTaiKhoanActivity(String tenTK, String soTien) {
        this.tenTK = tenTK;
        this.soTien = soTien;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }
}
