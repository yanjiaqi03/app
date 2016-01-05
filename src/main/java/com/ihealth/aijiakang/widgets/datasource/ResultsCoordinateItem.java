package com.ihealth.aijiakang.widgets.datasource;

/**
 * Created by YanJiaqi on 15/12/9
 */
public class ResultsCoordinateItem {
    private int value = 0;
    private boolean isFill = false;
    private int color = 0x00000000;

    public ResultsCoordinateItem(){

    }

    public ResultsCoordinateItem(int value,boolean isFill,int color){
        this.value = value;
        this.isFill = isFill;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFill() {
        return isFill;
    }

    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
