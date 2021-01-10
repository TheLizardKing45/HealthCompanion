package com.example.myapplication.activity.mental;


import java.security.Timestamp;
import java.sql.Time;

public class Mood {
    private int mood;
    private Timestamp timestamp;

    public Mood(int mood, Timestamp timestamp) {
        this.mood = mood;
        this.timestamp = timestamp;
    }
}
