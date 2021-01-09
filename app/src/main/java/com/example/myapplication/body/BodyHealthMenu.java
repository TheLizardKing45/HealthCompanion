package com.example.myapplication.body;

import java.util.ArrayList;

public class BodyHealthMenu {

    BodyHealthWeight bhw;
    BodyHealthPrescription bhp;
    BodyHealthExercise bhe;
    BodyHealthBloodPressure bhbp;
    ArrayList<String> todoList;


    public void presentButtons(){
        //stub
    }


    public void presentTodo() {
        //stub
    }


    public void addTodo(String task) {
        todoList.add(task);
    }


    public void removeTodo(String task) {
        todoList.remove(task);
    }
}
