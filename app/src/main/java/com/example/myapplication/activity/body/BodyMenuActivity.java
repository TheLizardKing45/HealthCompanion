package com.example.myapplication.activity.body;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class BodyMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_menu);
        getSupportActionBar().setTitle("Menu");
        configureGoalsButton();
        configureBodyHealthExerciseButton();
        configureBodyHealthBloodPressureButton();
        configureBodyHealthWeight();
    }


    //go to weight
    private void configureBodyHealthWeight() {
        Button bodyHealthWeightButton = (Button) findViewById(R.id.weightButton);
        bodyHealthWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BodyMenuActivity.this, BodyHealthWeightActivity.class));
            }
        });
    }


    //go to exercise
    private void configureBodyHealthExerciseButton() {
        Button bodyHealthExerciseButton = (Button) findViewById(R.id.exerciseButton);
        bodyHealthExerciseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(BodyMenuActivity.this, BodyHealthExerciseActivity.class));
            }
        });
    }

    //go to blood pressure
    private void configureBodyHealthBloodPressureButton() {
        Button bodyHealthBloodPressureButton = (Button) findViewById(R.id.bloodButton);
        bodyHealthBloodPressureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(BodyMenuActivity.this, BodyHealthBloodActivity.class));
            }
        });
    }


    //goes back
    private void configureGoalsButton() {
        Button goalsButton = (Button) findViewById(R.id.goalsButton);
        goalsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}