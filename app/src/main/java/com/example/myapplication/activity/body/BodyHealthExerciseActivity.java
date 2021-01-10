package com.example.myapplication.activity.body;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class BodyHealthExerciseActivity extends AppCompatActivity {


    private static final String FILE_NAME = "exercisesessions.text";
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private LineGraphSeries<DataPoint> series1;
    private ArrayList<Exercise> exerciseSessions;
    private long timeElapsed;

    public BodyHealthExerciseActivity() {
        exerciseSessions = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
        setContentView(R.layout.activity_body_health_exercise);
        configureBodyHealthMenuButton();
        configureWeightGraph();
        configureChronometer();
        configureExerciseInput();
    }

    public void addExerciseDuration() {
        String currentDate = Calendar.getInstance().getTime().toLocaleString();
        exerciseSessions.add(new Exercise(currentDate, timeElapsed));

    }

    private void configureExerciseInput() {


        Button submitButton = (Button) findViewById(R.id.exerciseSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addExerciseDuration();
                save();
                finish();
            }
        });
    }


    private void save() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            out.writeObject(exerciseSessions);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void load() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = openFileInput(FILE_NAME);
            in = new ObjectInputStream(fis);
            exerciseSessions = (ArrayList<Exercise>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void configureChronometer() {
        chronometer = findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
            }
        });
    }

    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    //renders weight
    private void configureWeightGraph() {

        GraphView graph = findViewById(R.id.exerciseGraph);
        GridLabelRenderer renderer = graph.getGridLabelRenderer();
        renderer.setGridStyle(GridLabelRenderer.GridStyle.NONE);

        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMaxY(200);
        viewport.setScalableY(true);


        series1 = new LineGraphSeries<>();
        series1.setTitle("Exercise Duration History");
        series1.setDrawDataPoints(true);
        series1.setThickness(8);


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);
        series1.setCustomPaint(paint);

        for (int x = 0; x < exerciseSessions.size(); x ++) {
            series1.appendData(new DataPoint(x + 1, exerciseSessions.get(x).getDuration()*0.001), true, 100);
        }
        series1.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(BodyHealthExerciseActivity.this, "Taken on " + exerciseSessions.get((int)dataPoint.getX() - 1).getDate(), Toast.LENGTH_SHORT).show();
            }
        });

        graph.addSeries(series1);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return super.formatLabel(value, isValueX);
                } else {
                    return super.formatLabel(value, isValueX) + "sec";
                }
            }
        });
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