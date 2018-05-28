package com.example.letsseatinmetro.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 이승헌 on 2018-05-28.
 */

public class Station {
    @SerializedName("realtimePositionList")
    @Expose
    private ArrayList<LineList> lineList=null;

    public ArrayList<LineList> getLineList() {
        return lineList;
    }

    public void setLineList(ArrayList<LineList> lineList) {
        this.lineList = lineList;
    }

}
