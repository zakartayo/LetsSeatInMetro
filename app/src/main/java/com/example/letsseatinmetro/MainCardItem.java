package com.example.letsseatinmetro;

/**
 * Created by Yong on 2018-03-03.
 */

public class MainCardItem {
    private String lineName;
    private int lineColor;
    private String lineRange;

    public MainCardItem(String lineName, int lineColor, String lineRange){
        this.lineName = lineName;
        this.lineColor = lineColor;
        this.lineRange = lineRange;
    }

    public void setLineName(String lineName){
        this.lineName = lineName;
    }
    public String getLineName(){
        return this.lineName;
    }
    public void setLineColor(int lineColor){
        this.lineColor = lineColor;
    }
    public int getLineColor(){
        return this.lineColor;
    }
    public void setLineRange(String lineRange){
        this.lineRange = lineRange;
    }
    public  String getLineRange(){
        return this.lineRange;
    }
}
