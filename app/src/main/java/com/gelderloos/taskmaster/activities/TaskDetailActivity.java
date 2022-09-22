package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gelderloos.taskmaster.R;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String TASK_TITLE_EXTRA_TAG = "taskTitle";
    public static final String TASK_BODY_EXTRA_TAG = "taskBody";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("taskTitle");
        TextView titleText = findViewById(R.id.textViewTaskDetailTitle);
        titleText.setText(title);
        String body = intent.getStringExtra("taskBody");
        TextView bodyText = findViewById(R.id.textViewTaskDetailDescription);
        bodyText.setText(body);
    }
}