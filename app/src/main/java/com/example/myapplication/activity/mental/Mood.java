package com.example.myapplication.activity.mental;


import java.io.Serializable;

public class Mood implements Serializable {
    private int mood;
    private String timestamp;

    public Mood(String timestamp, int mood) {
        this.timestamp = timestamp;
        this.mood = mood;
    }


    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
