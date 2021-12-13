package com.example.KeHoachMuaSam.model;

public class DanhSachItem {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private int itemCompleted;

    public DanhSachItem(int itemId, String itemName, double itemPrice, int itemCompleted) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCompleted = itemCompleted;
    }

    public int getItemCompleted() {
        return itemCompleted;
    }

    public void setItemCompleted(int itemCompleted) {
        this.itemCompleted = itemCompleted;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
