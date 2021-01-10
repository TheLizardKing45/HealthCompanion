package com.example.myapplication.activity.body;

import java.io.Serializable;

public class BloodPressure implements Serializable {

    private double bp;
    private String date;

    public BloodPressure(String date, double bp) {
        this.bp = bp;
        this.date = date;
    }

    public double getBp() {
        return bp;
    }

    public void setBp(double bp) {
        this.bp = bp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
