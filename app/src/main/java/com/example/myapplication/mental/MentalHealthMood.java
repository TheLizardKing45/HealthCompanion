package com.example.myapplication.mental;

import android.app.Activity;

import java.util.ArrayList;

public class MentalHealthMood extends Activity {
    private ArrayList<Integer> moodHistory;

    // add mood response to the list of
    public void addMood(Integer m) {
        moodHistory.add(m);
    }

    // remove mood at given index
    public void removeMood(int index) {
        moodHistory.remove(index);
    }


    public void presentGraph() {

    }


    // sends a push notification to phone
    public void remind() {

    }

}
