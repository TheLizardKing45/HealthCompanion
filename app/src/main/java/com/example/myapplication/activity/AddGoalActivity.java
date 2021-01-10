package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.body.BodyMenuActivity;
import com.example.myapplication.activity.mental.MentalMenuActivity;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        getSupportActionBar().setTitle("Add task");
        configureBodyButton();
        configureMentalButton();
    }

    private void configureBodyButton() {
        Button bodyButton = (Button) findViewById(R.id.bodyButton);
        bodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddGoalActivity.this, BodyMenuActivity.class));
            }
        });
    }


    private void configureMentalButton() {
        Button mentalButton = (Button) findViewById(R.id.mentalButton);
        mentalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddGoalActivity.this, MentalMenuActivity.class));
            }
        });
    }
}
