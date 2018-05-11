package com.example.letsseatinmetro.DialogItem;

import com.example.letsseatinmetro.R;

import lombok.Data;

/**
 * Created by multimedia on 2018-05-10.
 */
@Data
public class Congestion {
    public int congestionCode;
    public String congestionTitle;
    public String congestionDesc;
    public String vacancyTitle;
    public String vacancyCount;
    public int congestionPrice;
    public int congestionImageResource;
    public boolean isSelected;

    public Congestion() {
        congestionCode = 0;
        congestionTitle = "";
        congestionDesc = "혼잡도";
        congestionPrice = 0;
        vacancyTitle="";
        vacancyCount="";
        isSelected = false;
        congestionImageResource = 0;
    }

    public Congestion(int congestionCode, String congestionTitle, String congestionDesc, int congestionPrice) {
        this.congestionCode = congestionCode;
        this.congestionTitle = congestionTitle;
        this.congestionDesc = congestionDesc;
        this.congestionPrice = congestionPrice;
        this.congestionImageResource = 0;
        this.isSelected = false;
    }

    public Congestion(int congestionCode, String congestionTitle, String congestionDesc, String vacancyTitle, String vacancyCount, int congestionPrice, int congestionImageResource) {
        this.congestionCode = congestionCode;
        this.congestionTitle = congestionTitle;
        this.congestionDesc = congestionDesc;
        this.congestionPrice = congestionPrice;
        this.vacancyTitle = vacancyTitle;
        this.vacancyCount = vacancyCount;
        this.congestionImageResource = congestionImageResource;
        this.isSelected = false;
    }

    public Congestion(int congestionCode, String congestionTitle, String congestionDesc, int congestionPrice, int congestionImageResource, boolean isSelected) {
        this.congestionCode = congestionCode;
        this.congestionTitle = congestionTitle;
        this.congestionDesc = congestionDesc;
        this.congestionPrice = congestionPrice;
        this.congestionImageResource = congestionImageResource;
        this.isSelected = isSelected;
    }
}
