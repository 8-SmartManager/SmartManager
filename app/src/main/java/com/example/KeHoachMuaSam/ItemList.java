package com.example.KeHoachMuaSam;

import java.io.Serializable;

public class ItemList implements Serializable {
    private  String itemName;
    private String itemPrice;

    public ItemList(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public  String toString(){
        return itemName + itemPrice;
    }
}
