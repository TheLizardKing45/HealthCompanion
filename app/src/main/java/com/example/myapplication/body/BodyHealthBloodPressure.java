package com.example.myapplication.body;

import java.util.ArrayList;

public class BodyHealthBloodPressure {

    private ArrayList<Integer> bloodPressures;

    public void presentGraph() {
        //stub
    }


    public void presentHistory() {
        //stub
    }

    public void addBlood(int bp) {
        bloodPressures.add(bp);
    }


    public void removeBlood(int bp) {
        bloodPressures.remove(bp);
    }

    public void remind() {
        //stub

    }


}
