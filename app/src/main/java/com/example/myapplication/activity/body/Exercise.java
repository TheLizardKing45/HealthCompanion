package com.example.myapplication.activity.body;

import android.widget.EditText;

import java.io.Serializable;

public class Exercise implements Serializable {
    private double duration;
    private String date;

    public Exercise(String date, double duration) {
        this.date = date;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
