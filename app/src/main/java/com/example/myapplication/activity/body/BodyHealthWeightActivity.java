package com.example.myapplication.activity.body;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BodyHealthWeightActivity extends AppCompatActivity {


    private LineGraphSeries<DataPoint> series1;
    private ArrayList<Double> weights;


    public BodyHealthWeightActivity() {
        weights = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_health_weight);
        configureBodyHealthMenuButton();
        configureWeightGraph();
        configureWeightInput();
    }

    private void save(View v) {

    }

    private void load(View v) {

    }




    public void addWeight(double lb) {
        weights.add(lb);

    }




    private void configureWeightInput() {
        EditText weightInput = (EditText) findViewById(R.id.weightInput);
        Button submitButton = (Button) findViewById(R.id.weightSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addWeight(Double.parseDouble(weightInput.getText().toString()));
                finish();
            }
        });
    }

    //renders weight graph
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