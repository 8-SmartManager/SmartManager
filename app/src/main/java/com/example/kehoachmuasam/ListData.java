package com.example.kehoachmuasam;

import java.io.Serializable;

public class ListData implements Serializable {
    private int listID;


    private String listTitle;
    private int listCompleted;
    private int listTotal;
    private double listPrice;



    public ListData(int listID, String listTitle, int listCompleted, int listTotal, double listPrice) {
        this.listID = listID;
        this.listTitle = listTitle;
        this.listCompleted = listCompleted;
        this.listTotal = listTotal;
        this.listPrice = listPrice;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getListCompleted() {
        return listCompleted;
    }

    public void setListCompleted(int listCompleted) {
        this.listCompleted = listCompleted;
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
