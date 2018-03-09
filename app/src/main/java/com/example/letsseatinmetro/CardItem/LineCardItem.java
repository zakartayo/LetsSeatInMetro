package com.example.letsseatinmetro.CardItem;

/**
 * Created by 이승헌 on 2018-03-06.
 */

public class LineCardItem {
    private int top_img;
    private int line1;
    private int line2;
    private int bottom_img;
    private String  station;
    private String destination_top;
    private String destination_bottom;

    public LineCardItem(int top_img, int line1, int line2, int bottom_img, String station, String destination_top, String destination_bottom) {
        this.top_img = top_img;
        this.line1 = line1;
        this.line2 = line2;
        this.bottom_img = bottom_img;
        this.station = station;
        this.destination_top = destination_top;
        this.destination_bottom = destination_bottom;
    }
    public LineCardItem(int line, String station){
        this.line1 = line;
        this.line2 = line;
        this.station = station;
        this.destination_bottom = null;
        this.destination_top = null;
        this.top_img = 0;
        this.bottom_img = 0;
    }

    public void setTop_img(int top_img){
        this.top_img = top_img;
    }
    public void setline1(int line1){
        this.line1 = line1;
    }
    public void setline2(int line2){
        this.line2 = line2;
    }
    public void setBottom_img(int bottom_img){
        this.bottom_img = bottom_img;
    }
    public void setStation(String station){
        this.station = station;
    }
    public void setDestination_top(String destination_top){
        this.destination_top = destination_top;
    }
    public void setDestination_bottom(String destination_bottom){
        this.destination_bottom = destination_bottom;
    }
    public int getTop_img(){
        return this.top_img;
    }
    public int getBottom_img(){
        return this.bottom_img;
    }
    public int getLine1(){
        return this.line1;
    }
    public int getLine2() {
        return this.line2;
    }
    public String getStation(){
        return this.station;
    }
    public String getDestination_top(){
        return this.destination_top;
    }
    public String getDestination_bottom(){
        return this.destination_bottom;
    }
}
