package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.Task;
import com.gelderloos.taskmaster.R;

import java.util.List;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String TASK_TITLE_EXTRA_TAG = "taskTitle";
    public static final String TASK_BODY_EXTRA_TAG = "taskBody";
    public static final String TASK_STATE_EXTRA_TAG = "taskState";
    public static final String DATABASE_NAME = "task_list_db";
    List<Task> tasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("taskTitle");
        TextView titleText = findViewById(R.id.textViewTaskDetailTitle);
        titleText.setText(title);
        String body = intent.getStringExtra("taskBody");
        TextView bodyText = findViewById(R.id.textViewTaskDetailBody);
        bodyText.setText(body);
        String state = intent.getStringExtra("taskState");
        TextView taskState = findViewById(R.id.textViewTaskDetailState);
        taskState.setText("Status: " + state);
    }
}