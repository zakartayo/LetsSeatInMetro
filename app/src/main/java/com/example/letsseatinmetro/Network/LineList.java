package com.example.letsseatinmetro.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 이승헌 on 2018-05-28.
 */

public class LineList {
    @SerializedName("statnNm")
    @Expose
    private String statnNm;

    @SerializedName("trainNo")
    @Expose
    private String trainNo;

    @SerializedName("statnTnm")
    @Expose
    private String statnTnm;

    @SerializedName("updnLine")
    @Expose
    private String updnLine;

    @SerializedName("trainSttus")
    @Expose
    private String trainSttus;

    @SerializedName("directAt")
    @Expose
    private String directAt;


    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStatnTnm() {
        return statnTnm;
    }

    public void setStatnTnm(String statnTnm) {
        this.statnTnm = statnTnm;
    }

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getTrainSttus() {
        return trainSttus;
    }

    public void setTrainSttus(String trainSttus) {
        this.trainSttus = trainSttus;
    }

    public String getDirectAt() {
        return directAt;
    }

    public void setDirectAt(String directAt) {
        this.directAt = directAt;
    }
}