package com.example.myapplication.activity.mental;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activity.body.BodyHealthWeightActivity;
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

public class MentalHealthMoodActivity extends AppCompatActivity {

    private static final String FILE_NAME = "moods.txt";
    private ArrayList<Mood> moodHistory;
    private LineGraphSeries<DataPoint> series1;
    private float currRating;

    public MentalHealthMoodActivity() {
        moodHistory = new ArrayList<>();
        currRating = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
        setContentView(R.layout.activity_mental_health_mood);
        getSupportActionBar().setTitle("Mood (temporary screen)");
        configureMentalHealthButton();
        configureMentalGraph();
        configureMoodInput();

    }

    private void save() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            out.writeObject(moodHistory);

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
            moodHistory = (ArrayList<Mood>) in.readObject();

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

    public void addMood(double lb) {
        String currentDate = Calendar.getInstance().getTime().toLocaleString();
        //String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        moodHistory.add(new Mood(currentDate, (int) lb));
        save();

    }

    private void configureMoodInput() {
        RatingBar moodInput = (RatingBar) findViewById(R.id.ratingBar);
        moodInput.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currRating = rating;
            }
        });
    }


    //save

    //load

    //renders graph
    private void configureMentalGraph() {
        GraphView graph = findViewById(R.id.mentalGraph);
        GridLabelRenderer renderer = graph.getGridLabelRenderer();
        renderer.setGridStyle(GridLabelRenderer.GridStyle.NONE);

        Viewport viewport = graph.getViewport();

        viewport.setYAxisBoundsManual(true);
        viewport.setMaxY(5);
        viewport.setScalableY(true);


        series1 = new LineGraphSeries<>();
        series1.setTitle("Mood History");
        series1.setDrawDataPoints(true);
        series1.setThickness(8);


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);
        series1.setCustomPaint(paint);

        for (int x = 0; x < moodHistory.size(); x ++) {
            series1.appendData(new DataPoint(x + 1, moodHistory.get(x).getMood()), true, 100);
        }
        series1.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(MentalHealthMoodActivity.this, "Taken on " + moodHistory.get((int)dataPoint.getX() - 1).getTimestamp(), Toast.LENGTH_SHORT).show();
            }
        });

        graph.addSeries(series1);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return super.formatLabel(value, isValueX);
                } else {
                    return super.formatLabel(value, isValueX) + "stars";
                }
            }
        });
    }


    private void configureMentalHealthButton() {
        Button mentalMenuButton = (Button) findViewById(R.id.mentalMenuButton);
        mentalMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (currRating > 0.0) {
                    addMood((double) currRating);
                }
                finish();
            }
        });
    }

    // remove mood at given index
    public void removeMood(int index) {
        moodHistory.remove(index);
    }


}