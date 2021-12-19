package com.example.muctieutietkiem.muctieu.model;

public class TheLoai {
    private String TenTheLoai;
    private int icon;

    public TheLoai(String tenTheLoai, int icon) {
        TenTheLoai = tenTheLoai;
        this.icon = icon;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
