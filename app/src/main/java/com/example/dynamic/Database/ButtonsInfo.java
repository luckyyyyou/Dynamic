package com.example.dynamic.Database;

import org.litepal.crud.DataSupport;

/**
 * Created by Danni on 2018/5/9.
 */

public class ButtonsInfo extends DataSupport {
    private int bId;
    private int bWidth;
    private int bHeight;
    private int bColor;
    private float b_X;
    private float b_Y;
    private String bText;

    public int getbWidth() {
        return bWidth;
    }

    public void setbWidth(int bWidth) {
        this.bWidth = bWidth;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getbHeight() {
        return bHeight;
    }

    public void setbHeight(int bHeight) {
        this.bHeight = bHeight;
    }

    public int getbColor() {
        return bColor;
    }

    public void setbColor(int bColor) {
        this.bColor = bColor;
    }

    public String getbText() {
        return bText;
    }

    public void setbText(String bText) {
        this.bText = bText;
    }
    public float getB_X() {
        return b_X;
    }

    public void setB_X(float b_X) {
        this.b_X = b_X;
    }

    public float getB_Y() {
        return b_Y;
    }

    public void setB_Y(float b_Y) {
        this.b_Y = b_Y;
    }

}
