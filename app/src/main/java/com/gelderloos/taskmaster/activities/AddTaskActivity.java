package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.*;
import com.gelderloos.taskmaster.R;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    public static final String Tag = "AddTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


//        setUpEditTexts();
        setUpTypeSpinner();
        setUpSubmitButton();
    }

    private void setUpEditTexts() {
        EditText enterTaskTitle = ((EditText)findViewById(R.id.editTextAddTaskTaskTitle));
        EditText enterTaskBody = ((EditText)findViewById(R.id.editTextAddTaskTaskBody));
        enterTaskTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                enterTaskTitle.setText("");
            }
            });
        enterTaskBody.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                enterTaskBody.setText("");
            }
            });
    }

    private void setUpTypeSpinner(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        taskStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                TaskStatusEnum.values()
        ));

    }

    private void setUpSubmitButton(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        Button saveNewTaskButton = findViewById(R.id.buttonAddTaskSubmit);
        saveNewTaskButton.setOnClickListener(view -> {
            String taskTitle = ((EditText) findViewById(R.id.editTextAddTaskTaskTitle)).getText().toString();
            String taskBody = ((EditText) findViewById(R.id.editTextAddTaskTaskBody)).getText().toString();
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());

            NewTask newTask = NewTask.builder()
                    .taskTitle(taskTitle)
                    .taskDateCreated(new Temporal.DateTime(currentDateString))
                    .taskBody(taskBody)
                    .taskStatus((TaskStatusEnum) taskStateSpinner.getSelectedItem())
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    successResponse -> Log.i(Tag, "AddTaskActivity: task added!"),
                    failureResponse -> Log.i(Tag, "AddTaskActivity: failed with this response: " + failureResponse)
            );

            Intent goToMainActivity = new Intent(AddTaskActivity.this, MainActivity.class);
            startActivity(goToMainActivity);

        });
    }

}