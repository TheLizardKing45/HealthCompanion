package com.example.myapplication.activity.body;

import java.io.Serializable;

public class Weight implements Serializable {

    private String date;
    private double lbs;

    public Weight(String date, double lbs) {
        this.date = date;
        this.lbs = lbs;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public double getLbs() {
        return lbs;
    }

    public void setLbs(double lbs) {
        this.lbs = lbs;
    }
}
