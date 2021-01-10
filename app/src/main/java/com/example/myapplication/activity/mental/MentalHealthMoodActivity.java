package com.example.myapplication.activity.mental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MentalHealthMoodActivity extends AppCompatActivity {

    private ArrayList<Mood> moodHistory;
    private LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_health_mood);
        configureMentalHealthButton();
        configureMentalGraph();
    }


    //renders graph
    private void configureMentalGraph() {
        double x,y;
        x = 0;

        GraphView graph = findViewById(R.id.mentalGraph);
        series1 = new LineGraphSeries<>();
        int dataPoints = 500;

        for (int i = 0; i < dataPoints; i++) {
            x = x + 0.1;
            y = Math.sin(x);
            series1.appendData(new DataPoint(x,y), true, 100);
        }
        graph.addSeries(series1);


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