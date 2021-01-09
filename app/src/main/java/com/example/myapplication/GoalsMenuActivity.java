package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GoalsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureBodyButton();
        configureMentalButton();
    }


    private void configureBodyButton() {
        Button bodyButton = (Button) findViewById(R.id.bodyButton);
        bodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalsMenuActivity.this, BodyMenuActivity.class));
            }
        });
    }


    private void configureMentalButton() {
        Button mentalButton = (Button) findViewById(R.id.mentalButton);
        mentalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalsMenuActivity.this, MentalMenuActivity.class));
            }
        });
    }
}