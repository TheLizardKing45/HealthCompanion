package com.example.myapplication.activity.mental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.myapplication.R;
import com.example.myapplication.activity.body.Weight;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
            in  = new ObjectInputStream(fis);
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

        moodHistory.add(new Mood(currentDate,(int) lb));
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
                if (!(currRating  == 0.0)) {
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