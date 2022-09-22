package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.adapters.TaskListRecyclerViewAdapter;
import com.gelderloos.taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    TextView userTasks;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


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
    }

        private void setUpTaskListRecyclerView(){
            RecyclerView taskListRecyclerView = findViewById(R.id.recyclerMainActivityTaskRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            taskListRecyclerView.setLayoutManager(layoutManager);

            List<Task> tasks = new ArrayList<>();

            tasks.add(new Task("title of test task 1", "body of test task 1 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
            tasks.add(new Task("title of test task 2", "body of test task 2 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
            tasks.add(new Task("title of test task 3", "body of test task 3 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));

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

    public void addTaskButton(View view){
        Intent goToAddTaskActivity = new Intent(this, SettingsActivity.class);
        startActivity(goToAddTaskActivity);
    }

    }