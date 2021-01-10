package com.example.myapplication.activity.body;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class BodyHealthWeightActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_health_weight);
        configureBodyHealthMenuButton();
        configureWeightGraph();
    }


    //renders weight
    private void configureWeightGraph() {
        double x,y;
        x = 0;

        GraphView graph = findViewById(R.id.weightGraph);
        series1 = new LineGraphSeries<>();
        int dataPoints = 500;

        for (int i = 0; i < dataPoints; i++) {
            x = x + 0.1;
            y = Math.sin(x);
            series1.appendData(new DataPoint(x,y), true, 100);
        }
        graph.addSeries(series1);


    }

    //goes back
    private void configureBodyHealthMenuButton() {
        Button bodyHealthMenuButton = (Button) findViewById(R.id.bodyHealthMenuButton);
        bodyHealthMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}