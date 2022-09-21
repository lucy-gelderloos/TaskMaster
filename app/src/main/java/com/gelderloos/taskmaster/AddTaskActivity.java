package com.gelderloos.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button submitBttn = AddTaskActivity.this.findViewById(R.id.submitTaskButton);

        //2. add an event listener
        submitBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView greeting = AddTaskActivity.this.findViewById(R.id.textViewTaskSubmitted);
                greeting.setText("Submitted!");
            }
        });

    }
}