package com.example.KeHoachMuaSam;

public class ListData {

    private String listTitle;
    private int listTotal;
    private double listPrice;

    public ListData(String listTitle, int listTotal, double listPrice) {
        this.listTitle = listTitle;
        this.listTotal = listTotal;
        this.listPrice = listPrice;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public int getListTotal() {
        return listTotal;
    }

    public void setListTotal(int listTotal) {
        this.listTotal = listTotal;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

}
