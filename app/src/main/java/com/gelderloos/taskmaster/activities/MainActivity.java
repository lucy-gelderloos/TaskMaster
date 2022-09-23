package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.adapters.TaskListRecyclerViewAdapter;
import com.gelderloos.taskmaster.database.TaskListDatabase;
import com.gelderloos.taskmaster.models.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "task_list_db";
    SharedPreferences preferences;
    TextView userTasks;
    String username;
    TaskListDatabase taskListDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        taskListDatabase = Room.databaseBuilder(
                getApplicationContext(), //context is whole application so only one instance of database runs
                TaskListDatabase.class,
                DATABASE_NAME
        )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        taskListDatabase.taskDao().findAll();

        Button addTaskButton = MainActivity.this.findViewById(R.id.buttonMainActivityAddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTaskActivity.class);
                // Launch the intent
                startActivity(goToAddTaskActivity);
            }
        });

        Button allTasksButton = MainActivity.this.findViewById(R.id.buttonMainActivityAllTasks);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTasksActivity = new Intent(MainActivity.this, AllTasksActivity.class);
                // Launch the intent
                startActivity(goToAllTasksActivity);
            }
        });

        setUpTaskListRecyclerView();
        setUpAddTaskButton();

    }

    private void setUpAddTaskButton() {
        findViewById(R.id.buttonMainActivityAddTask).setOnClickListener(view -> {
            Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(goToAddTaskActivity);
        });
    }

//    private void setUpTaskDetailButton(){
//        findViewById(R.id.buttonMainActivityTaskDetail).setOnClickListener(view -> {
//            Intent goToTaskDetailActivity = new Intent(MainActivity.this, TaskDetailActivity.class);
//            startActivity(goToPokemanActivity);
//        });
//    }


        private void setUpTaskListRecyclerView(){
            RecyclerView taskListRecyclerView = findViewById(R.id.recyclerMainActivityTaskRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            taskListRecyclerView.setLayoutManager(layoutManager);

            List<Task> tasks = taskListDatabase.taskDao().findAll();

            TaskListRecyclerViewAdapter adapter = new TaskListRecyclerViewAdapter(tasks, this);
            taskListRecyclerView.setAdapter(adapter);

        }

    @Override
    protected void onResume() {
        super.onResume();

        username = preferences.getString(SettingsActivity.USER_NAME_TAG, "User");
        userTasks = (TextView)findViewById(R.id.textViewMainActivityUserTasks);
        userTasks.setText(username + "'s Tasks:");
    }

    public void taskDetailButton(View view){
            Intent goToTaskDetailActivity = new Intent(MainActivity.this, TaskDetailActivity.class);
            Button tappedButton = (Button)view;
            String buttonText = tappedButton.getText().toString();
            goToTaskDetailActivity.putExtra("taskTitle", buttonText);
            startActivity(goToTaskDetailActivity);
    }

    public void settingsButton(View view){
        Intent goToSettingsActivity = new Intent(this, SettingsActivity.class);
        startActivity(goToSettingsActivity);
    }

//    public void addTaskButton(View view){
//        Intent goToAddTaskActivity = new Intent(this, SettingsActivity.class);
//        startActivity(goToAddTaskActivity);
//    }

    }