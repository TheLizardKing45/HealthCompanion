package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.activity.body.BodyMenuActivity;
import com.example.myapplication.activity.mental.MentalMenuActivity;

import java.util.ArrayList;

public class GoalsMenuActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView todoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoItems = (ListView) findViewById(R.id.todoItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        todoItems.setAdapter(itemsAdapter);

        items.add("test 1");
        items.add("test 2");
        items.add("test 3");
        items.add("test 4");
        items.add("test 5");

        configureListViewListener();
        configureBodyButton();
        configureMentalButton();
    }

    private void configureListViewListener() {
        todoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
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