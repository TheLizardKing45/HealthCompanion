package com.example.myapplication.activity.mental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.R;
import com.example.myapplication.activity.body.BodyHealthBloodActivity;
import com.example.myapplication.activity.body.BodyMenuActivity;

public class MentalMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_menu);
        getSupportActionBar().setTitle("Menu (temporary screen)");
        configureGoalsButton();
        configureMentalHealthMoodButton();
    }

    private void configureMentalHealthMoodButton() {
        Button mentalHealthMoodButton = (Button) findViewById(R.id.mentalHealthMood);
        mentalHealthMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MentalMenuActivity.this, MentalHealthMoodActivity.class));
            }
        });
    }


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