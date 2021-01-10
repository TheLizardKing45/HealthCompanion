package com.example.myapplication.activity.mental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MentalHealthMoodActivity extends AppCompatActivity {

    private ArrayList<Mood> moodHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_health_mood);
        configureMentalHealthButton();
    }

    private void configureMentalHealthButton() {
        Button mentalMenuButton = (Button) findViewById(R.id.mentalMenuButton);
        mentalMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    // add mood response to the list of
    public void addMood(Mood m) {
        moodHistory.add(m);
    }

    // remove mood at given index
    public void removeMood(int index) {
        moodHistory.remove(index);
    }


}