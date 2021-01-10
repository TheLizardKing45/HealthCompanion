package com.example.myapplication.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.activity.body.BodyMenuActivity;
import com.example.myapplication.activity.mental.MentalMenuActivity;

import java.util.ArrayList;

public class GoalsMenuActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView todoItems;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Health Companion");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().hide();
        configureTodoList();

        items.add("test 1");
        items.add("test 2");
        items.add("test 3");
        items.add("test 4");
        items.add("test 5");

        configureListViewListener();
        configureBodyButton();
        configureMentalButton();
        configureAddButton();
    }

    private void configureTodoList() {
        todoItems = (ListView) findViewById(R.id.todoItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        todoItems.setAdapter(itemsAdapter);
    }

    private void configureAddButton() {

    }

    private void configureListViewListener() {
        todoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createConfirmDeleteDialog(position);
                return true;
            }
        });

    }

    private void createConfirmDeleteDialog(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(GoalsMenuActivity.this);
        alert.setTitle("Delete task?");
        alert.setMessage("Are you sure you want to delete this task?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("test");
                items.remove(position);
                dialog.dismiss();
                itemsAdapter.notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
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