package com.example.myapplication.activity.mental;


import java.security.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;

public class Mood {
    private int mood;
    private String timestamp;

    public Mood(int mood) {
        Calendar calendar = Calendar.getInstance();
        timestamp = DateFormat.getDateInstance().format(calendar.getTime());
        this.mood = mood;
    }
}
