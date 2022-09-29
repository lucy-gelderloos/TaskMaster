package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.*;
import com.gelderloos.taskmaster.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {
    public static final String Tag = "AddTaskActivity";
    SharedPreferences preferences;
    Team selectedTeam;
    List<String> teamNames = null;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        userTeamString = preferences.getString(SettingsActivity.USER_TEAM_TAG,null);

        teamNames = new ArrayList<>();

//        setUpEditTexts();
        setUpTypeSpinner();
        setUpTeamSpinner();
        setUpSubmitButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Amplify.API.query(
                // list gives ALL items, get() gives you 1
                ModelQuery.list(Team.class),
                successResponse -> {
                    Log.i(Tag, "Teams read successfully!");
                    teamNames.clear();
                    for (Team dataBaseTeam : successResponse.getData()){
                        teamNames.add(dataBaseTeam.getTeamName());
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },
                failureResponse -> Log.i(Tag, "Did not read Tasks successfully")
        );
    }

    private void setUpTypeSpinner(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        taskStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                TaskStatusEnum.values()
        ));
    }

    private void setUpTeamSpinner(){
        Spinner taskTeamSpinner = findViewById(R.id.spinnerAddTaskTeam);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                teamNames);
        taskTeamSpinner.setAdapter(adapter);
    }

    private void setUpSubmitButton(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        Button saveNewTaskButton = findViewById(R.id.buttonAddTaskSubmit);
        Spinner teamSpinner = findViewById(R.id.spinnerAddTaskTeam);
        saveNewTaskButton.setOnClickListener(view -> {
            String taskTitle = ((EditText) findViewById(R.id.editTextAddTaskTaskTitle)).getText().toString();
            String taskBody = ((EditText) findViewById(R.id.editTextAddTaskTaskBody)).getText().toString();
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());

            Amplify.API.query(
                    ModelQuery.list(Team.class),
                    successResponse -> {
                        for (Team dataBaseTeam : successResponse.getData()){
                            if(dataBaseTeam.getTeamName().equals(teamSpinner.getSelectedItem())) {
                                selectedTeam = dataBaseTeam;
                            }
                        }
                        runOnUiThread(() -> {

                        });
                    },
                    failureResponse -> Log.i(Tag, "Did not read Tasks successfully")
            );

            Task newTask = Task.builder()
                    .taskTitle(taskTitle)
                    .taskDateCreated(new Temporal.DateTime(currentDateString))
                    .taskBody(taskBody)
                    .taskStatus((TaskStatusEnum) taskStateSpinner.getSelectedItem())
                    .team(selectedTeam)
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