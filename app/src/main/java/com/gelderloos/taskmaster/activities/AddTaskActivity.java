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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTaskActivity extends AppCompatActivity {
    public static final String Tag = "AddTaskActivity";
    SharedPreferences preferences;
    Spinner taskTeamSpinner = null;
    CompletableFuture<List<Team>> teamFuture = null;
    String s3ImageKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        teamFuture = new CompletableFuture<>();

        setUpStateSpinner();
        setUpTeamSpinner();
        setUpImageButton();
        setUpSubmitButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        s3ImageKey = intent.getStringExtra("S3ImageKey");
    }

    private void setUpStateSpinner(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        taskStateSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                TaskStatusEnum.values()
        ));
    }

    private void setUpTeamSpinner(){
        taskTeamSpinner = findViewById(R.id.spinnerAddTaskTeam);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(Tag, "Read teams successfully");
                    ArrayList<String> teamNames = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();
                    for (Team team : success.getData()){
                        teams.add(team);
                        teamNames.add(team.getTeamName());
                    }
                    teamFuture.complete(teams);
                    runOnUiThread(() -> {
                        taskTeamSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_spinner_item,
                                teamNames));
                    });
                },
                failure -> {
                    teamFuture.complete(null); // Don't forget to complete a CompletableFuture on every code path!
                    Log.i(Tag, "Did not read teams successfully");
                }
        );
    }

    private void setUpSubmitButton(){
        Spinner taskStateSpinner = findViewById(R.id.spinnerAddTaskTaskState);
        Button saveNewTaskButton = findViewById(R.id.buttonAddTaskSubmit);
        saveNewTaskButton.setOnClickListener(view -> {
            String selectedTeamString = taskTeamSpinner.getSelectedItem().toString();
            List<Team> teams = null;
            try {
                teams = teamFuture.get();
            } catch (InterruptedException ie) {
                Log.e(Tag, "Interrupted Exception while getting teams");
                Thread.currentThread().interrupt();
            } catch (ExecutionException ee) {
                Log.e(Tag, "ExecutionException while getting trainers" + ee.getMessage());
            }

            Team selectedTeam = teams.stream().filter(t -> t.getTeamName().equals(selectedTeamString)).findAny().orElseThrow(RuntimeException::new);


            String taskTitle = ((EditText) findViewById(R.id.editTextAddTaskTaskTitle)).getText().toString();
            String taskBody = ((EditText) findViewById(R.id.editTextAddTaskTaskBody)).getText().toString();
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());


            Task newTask = Task.builder()
                    .taskTitle(taskTitle)
                    .taskDateCreated(new Temporal.DateTime(currentDateString))
                    .taskBody(taskBody)
                    .taskStatus((TaskStatusEnum) taskStateSpinner.getSelectedItem())
                    .team(selectedTeam)
                    .associatedImageS3Key(s3ImageKey)
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

    private void setUpImageButton(){
        findViewById(R.id.ButtonAddTaskAddImage).setOnClickListener(v -> {
            Intent goToAddImageActivity = new Intent(AddTaskActivity.this, ImageTaskActivity.class);
            startActivity(goToAddImageActivity);
        });
    }

}