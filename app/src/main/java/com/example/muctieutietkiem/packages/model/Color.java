package com.example.muctieutietkiem.packages.model;

public class Color {
    private String colorName;
    private int colorID;
    private int colorThumb;

    public Color(String colorName, int colorID, int colorThumb) {
        this.colorName = colorName;
        this.colorID = colorID;
        this.colorThumb = colorThumb;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public int getColorThumb() {
        return colorThumb;
    }

    public void setColorThumb(int colorThumb) {
        this.colorThumb = colorThumb;
    }
}
