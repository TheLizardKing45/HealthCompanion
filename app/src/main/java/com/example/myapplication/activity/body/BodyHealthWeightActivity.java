package com.example.myapplication.activity.body;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class BodyHealthWeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_health_weight);
        configureBodyHealthMenuButton();
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