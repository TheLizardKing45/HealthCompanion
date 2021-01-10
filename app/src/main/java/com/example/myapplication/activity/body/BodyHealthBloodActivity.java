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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class BodyHealthBloodActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;
    private ArrayList<BloodPressure> bloodList;
    private static final String FILE_NAME = "bloodpressures.txt";

    public BodyHealthBloodActivity() {
        bloodList = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
        setContentView(R.layout.activity_body_health_blood);
        configureBodyHealthMenuButton();
        configureBloodGraph();
        configureBloodInput();
    }
    public void addBloodPressure(double bp) {
        String currentDate = Calendar.getInstance().getTime().toLocaleString();
        //String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        bloodList.add(new BloodPressure(currentDate,bp));

    }

    private void configureBloodInput() {
        EditText bloodInput = (EditText) findViewById(R.id.bloodInput);
        Button submitButton = (Button) findViewById(R.id.bloodSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addBloodPressure(Double.parseDouble(bloodInput.getText().toString()));
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
            out.writeObject(bloodList);

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
            bloodList = (ArrayList<BloodPressure>) in.readObject();

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


    //renders weight
    private void configureBloodGraph() {
        double x,y;
        x = 0;

        GraphView graph = findViewById(R.id.bloodGraph);
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