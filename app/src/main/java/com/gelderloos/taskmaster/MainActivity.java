package com.gelderloos.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TASK_TITLE_EXTRA_TAG = "productName";
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
            }});

    Button allTasksButton = MainActivity.this.findViewById(R.id.buttonMainActivityAllTasks);
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

    @Override
    protected void onResume() {
        super.onResume();

        username = preferences.getString(SettingsActivity.USER_NAME_TAG, "User");
        userTasks = (TextView)findViewById(R.id.textViewMainActivityUserTasks);
        userTasks.setText(username + "'s Tasks:");
    }

    public void taskDetailButton(View view){
            Intent goToSettingsActivity = new Intent(MainActivity.this, TaskDetailActivity.class);
            Button tappedButton = (Button)view;
            String buttonText = tappedButton.getText().toString();
            goToSettingsActivity.putExtra("taskTitle", buttonText);
            startActivity(goToSettingsActivity);
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