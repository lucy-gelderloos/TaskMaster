package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.database.TaskListDatabase;
import com.gelderloos.taskmaster.database.TaskListDatabaseConverters;
import com.gelderloos.taskmaster.models.Task;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    TaskListDatabase taskListDatabase;
    public static final String DATABASE_NAME = "task_list_db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskListDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskListDatabase.class,
                        DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


        setUpTypeSpinner();
        setUpSubmitButton();
    }

    private void setUpTypeSpinner(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        taskStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                Task.StateEnum.values()
        ));

    }

    private void setUpSubmitButton(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        Button saveNewTaskButton = findViewById(R.id.buttonAddTaskSubmit);
        saveNewTaskButton.setOnClickListener(view -> {
            String taskTitle = ((EditText) findViewById(R.id.editTextAddTaskTaskTitle)).getText().toString();
            String taskBody = ((EditText) findViewById(R.id.editTextAddTaskTaskBody)).getText().toString();
            java.util.Date newDate = new Date();
            Task.StateEnum taskStateEnum = Task.StateEnum.fromString(taskStateSpinner.getSelectedItem().toString());

            Task newTask = new Task(taskTitle, taskBody, taskStateEnum);
            taskListDatabase.taskDao().insertATask(newTask);
            Intent goToMainActivity = new Intent(AddTaskActivity.this, MainActivity.class);
            startActivity(goToMainActivity);

        });
    }

}