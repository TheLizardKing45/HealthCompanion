package com.example.myapplication.activity.body;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        bloodList.add(new BloodPressure(currentDate, bp));

    }

    private void configureBloodInput() {
        EditText bloodInput = (EditText) findViewById(R.id.bloodInput);
        Button submitButton = (Button) findViewById(R.id.bloodSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = bloodInput.getText().toString();
                if (text.length() > 0) {
                    addBloodPressure(Double.parseDouble(bloodInput.getText().toString()));
                    save();
                }
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
            in = new ObjectInputStream(fis);
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

        GraphView graph = findViewById(R.id.bloodGraph);
        GridLabelRenderer renderer = graph.getGridLabelRenderer();
        renderer.setGridStyle(GridLabelRenderer.GridStyle.NONE);

        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMaxY(200);
        viewport.setScalableY(true);


        series1 = new LineGraphSeries<>();
        series1.setTitle("Systolic Blood Pressure History");
        series1.setDrawDataPoints(true);
        series1.setThickness(8);


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);
        series1.setCustomPaint(paint);

        for (int x = 0; x < bloodList.size(); x ++) {
            series1.appendData(new DataPoint(x + 1, bloodList.get(x).getBp()), true, 100);
        }
        series1.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(BodyHealthBloodActivity.this, "Taken on " + bloodList.get((int)dataPoint.getX() - 1).getDate(), Toast.LENGTH_SHORT).show();
            }
        });

        graph.addSeries(series1);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return super.formatLabel(value, isValueX);
                } else {
                    return super.formatLabel(value, isValueX) + "mm Hg";
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