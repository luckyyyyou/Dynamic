package com.example.dynamic.db;

/**
 * Created by Danni on 2018/5/9.
 */

public class textInfo {
    private int tId;
    private int tWidth;
    private int tHeight;
    private int tColor;
    private String tText;

    public String gettText() {
        return tText;
    }

    public void settText(String tText) {
        this.tText = tText;
    }

    public int gettColor() {
        return tColor;
    }

    public void settColor(int tColor) {
        this.tColor = tColor;
    }

    public int gettHeight() {
        return tHeight;
    }

    public void settHeight(int tHeight) {
        this.tHeight = tHeight;
    }

    public int gettWidth() {
        return tWidth;
    }

    public void settWidth(int tWidth) {
        this.tWidth = tWidth;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }


}
