package com.example.muctieutietkiem.packages.model;

import java.util.Date;

public class MucTieuTietKiem {

    private String TenMucTieu;
    private double SoTienMucTieu;
    private double SoTienDatDuoc;
    private Date NgayKetThuc;
    private String LuuY;

    public MucTieuTietKiem(String tenMucTieu, double soTienMucTieu, double soTienDatDuoc, Date ngayKetThuc, String luuY) {
        TenMucTieu = tenMucTieu;
        SoTienMucTieu = soTienMucTieu;
        SoTienDatDuoc = soTienDatDuoc;
        NgayKetThuc = ngayKetThuc;
        LuuY = luuY;
    }

    public String getTenMucTieu() {
        return TenMucTieu;
    }

    public void setTenMucTieu(String tenMucTieu) {
        TenMucTieu = tenMucTieu;
    }

    public double getSoTienMucTieu() {
        return SoTienMucTieu;
    }

    public void setSoTienMucTieu(double soTienMucTieu) {
        SoTienMucTieu = soTienMucTieu;
    }

    public double getSoTienDatDuoc() {
        return SoTienDatDuoc;
    }

    public void setSoTienDatDuoc(double soTienDatDuoc) {
        SoTienDatDuoc = soTienDatDuoc;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getLuuY() {
        return LuuY;
    }

    public void setLuuY(String luuY) {
        LuuY = luuY;
    }
}
