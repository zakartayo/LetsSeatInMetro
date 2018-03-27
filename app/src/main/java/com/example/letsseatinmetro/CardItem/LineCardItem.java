package com.example.letsseatinmetro.CardItem;

import com.example.letsseatinmetro.R;

/**
 * Created by 이승헌 on 2018-03-06.
 */

public class LineCardItem {
    private int top_img_1;
    private int top_img_2;
    private int top_img_3;
    private int line1;
    private int line2;
    private int bottom_img_1;
    private int bottom_img_2;
    private int bottom_img_3;
    private String  station;
    private String destination_top_1;
    private String destination_top_2;
    private String destination_top_3;
    private String destination_bottom_1;
    private String destination_bottom_2;
    private String destination_bottom_3;


    public LineCardItem(int top_img, int line1, int line2, int bottom_img, String station, String destination_top, String destination_bottom) {
        this.top_img_1 = top_img;
        this.top_img_2 = top_img;
        this.top_img_3 = top_img;
        this.bottom_img_1 = bottom_img;
        this.bottom_img_2 = bottom_img;
        this.bottom_img_3 = bottom_img;

        this.line1 = line1;
        this.line2 = line2;

        this.station = station;
        this.destination_top_1 = destination_top;
        this.destination_top_2 = destination_top;
        this.destination_top_3 = destination_top;
        this.destination_bottom_1 = destination_bottom;
        this.destination_bottom_2 = destination_bottom;
        this.destination_bottom_3 = destination_bottom;
    }
    public LineCardItem(int line, String station){
        this.line1 = line;
        this.line2 = line;
        this.station = station;
        this.destination_bottom_1 = null;
        this.destination_bottom_2 = null;
        this.destination_bottom_3 = null;
        this.destination_top_1 = null;
        this.destination_top_2 = null;
        this.destination_top_3 = null;
        this.top_img_1= R.drawable.blank_img;
        this.top_img_2= R.drawable.blank_img;
        this.top_img_3= R.drawable.blank_img;
        this.bottom_img_1=R.drawable.blank_img;
        this.bottom_img_2=R.drawable.blank_img;
        this.bottom_img_3=R.drawable.blank_img;
    }
    public void setTop_img_1(int top_img){
        this.top_img_1 = top_img;
    }
    public void setTop_img_2(int top_img){
        this.top_img_2 = top_img;
    }
    public void setTop_img_3(int top_img){
        this.top_img_3 = top_img;
    }
    public void setline1(int line1){
        this.line1 = line1;
    }
    public void setline2(int line2){
        this.line2 = line2;
    }
    public void setBottom_img_1(int bottom_img){
        this.bottom_img_1 = bottom_img;
    }
    public void setBottom_img_2(int bottom_img){
        this.bottom_img_2 = bottom_img;
    }
    public void setBottom_img_3(int bottom_img){
        this.bottom_img_3 = bottom_img;
    }
    public void setStation(String station){
        this.station = station;
    }
    public void setDestination_top_1(String destination_top){
        this.destination_top_1 = destination_top;
    }
    public void setDestination_top_2(String destination_top){
        this.destination_top_2 = destination_top;
    }
    public void setDestination_top_3(String destination_top){
        this.destination_top_3 = destination_top;
    }
    public void setDestination_bottom_1(String destination_bottom){
        this.destination_bottom_1 = destination_bottom;
    }
    public void setDestination_bottom_2(String destination_bottom){
        this.destination_bottom_2 = destination_bottom;
    }
    public void setDestination_bottom_3(String destination_bottom){
        this.destination_bottom_3 = destination_bottom;
    }
    public int getTop_img_1(){
        return this.top_img_1;
    }
    public int getTop_img_2(){
        return this.top_img_2;
    }
    public int getTop_img_3(){
        return this.top_img_3;
    }
    public int getBottom_img_1(){
        return this.bottom_img_1;
    }
    public int getBottom_img_2(){
        return this.bottom_img_2;
    }
    public int getBottom_img_3(){
        return this.bottom_img_3;
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
    public String getDestination_top_1(){
        return this.destination_top_1;
    }
    public String getDestination_top_2(){
        return this.destination_top_2;
    }
    public String getDestination_top_3(){
        return this.destination_top_3;
    }
    public String getDestination_bottom_1(){
        return this.destination_bottom_1;
    }
    public String getDestination_bottom_2(){
        return this.destination_bottom_2;
    }
    public String getDestination_bottom_3(){
        return this.destination_bottom_3;
    }

}
