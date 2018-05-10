package com.example.dynamic.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Danni on 2018/5/10.
 */

public class EditTextsInfo extends DataSupport {

    private int eId;
    private float e_X;
    private float e_Y;
    private String eText;


    public String geteText() {
        return eText;
    }

    public void seteText(String eText) {
        this.eText = eText;
    }

    public float getE_Y() {
        return e_Y;
    }

    public void setE_Y(float e_Y) {
        this.e_Y = e_Y;
    }

    public float getE_X() {
        return e_X;
    }

    public void setE_X(float e_X) {
        this.e_X = e_X;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

}
