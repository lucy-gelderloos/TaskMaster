package com.gelderloos.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button addTaskButton = MainActivity.this.findViewById(R.id.mainActivityButtonAddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTaskActivity.class);
                // Launch the intent
                startActivity(goToAddTaskActivity);
            }});

    Button allTasksButton = MainActivity.this.findViewById(R.id.mainActivityButtonAllTasks);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTasksActivity = new Intent(MainActivity.this, AllTasksActivity.class);
                // Launch the intent
                startActivity(goToAllTasksActivity);
            }});

//    public void submitTask(View view) {
//        Intent intent = new Intent(this, AddTaskActivity.class);
//        EditText editTextTitle = (EditText) findViewById(R.id.editTextTextTaskTitle);
//        EditText editTextDescription = (EditText) findViewById(R.id.editTextTextTaskDescription);
//        String taskTitle = editTextTitle.getText().toString();
//        String taskDescription = editTextDescription.getText().toString();
//        startActivity(intent);
//    }



}
    }