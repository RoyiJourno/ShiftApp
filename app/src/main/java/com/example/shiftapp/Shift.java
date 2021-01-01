package com.example.shiftapp;

public class Shift {
    private String insertTime;
    private String exitTime;
    private String totalTime;
    private String date;

    public Shift(){

    }

    public Shift(String insertTime, String exitTime, String totalTime,String date) {
        this.insertTime = insertTime;
        this.exitTime = exitTime;
        this.totalTime = totalTime;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
