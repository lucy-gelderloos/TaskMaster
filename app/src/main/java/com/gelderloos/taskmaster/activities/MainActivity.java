package com.gelderloos.taskmaster.activities;

import static com.gelderloos.taskmaster.activities.AddTaskActivity.Tag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.*;
import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.adapters.TaskListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SharedPreferences preferences;
    public AuthUser currentUser = null;
    TextView userTasksTV;
    TextView userTeamTV;
    String username;
    String userTeam;

    List<Task> tasks = null;
    TaskListRecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasks = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentUser = Amplify.Auth.getCurrentUser();

        setUpTaskListRecyclerView();
        setUpAddTaskButton();
        setUpAllTasksButton();
        setUpSettingsButton();
        setUpAuthButtons();
    }

    private void setUpAddTaskButton() {
        findViewById(R.id.buttonMainActivityAddTask).setOnClickListener(view -> {
            Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(goToAddTaskActivity);
        });
    }

    public void setUpAllTasksButton() {
        findViewById(R.id.buttonMainActivityAllTasks).setOnClickListener(view -> {
            Intent goToAllTasksActivity = new Intent(MainActivity.this, AllTasksActivity.class);
            startActivity(goToAllTasksActivity);
        });
    }

    public void setUpSettingsButton() {
        findViewById(R.id.buttonMainActivityToSettings).setOnClickListener(view -> {
            Intent goToSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(goToSettingsActivity);
        });
    }

    private void setUpTaskListRecyclerView() {
        RecyclerView taskListRecyclerView = findViewById(R.id.recyclerAllTasksActivityTaskRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskListRecyclerView.setLayoutManager(layoutManager);
        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);
    }

    private void setUpAuthButtons(){
        Button logInButton = findViewById(R.id.buttonMainActivityLogIn);
        Button signUpButton = findViewById(R.id.buttonMainActivitySignUp);
        Button logOutButton = findViewById(R.id.buttonMainActivityLogOut);

        if (currentUser == null) {
            logInButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
            logOutButton.setVisibility(View.INVISIBLE);
        } else {
            logInButton.setVisibility(View.INVISIBLE);
            signUpButton.setVisibility(View.INVISIBLE);
            logOutButton.setVisibility(View.VISIBLE);
        }
        logInButton.setOnClickListener(view -> {
            Intent goToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goToLoginActivity);
        });

        signUpButton.setOnClickListener(view -> {
            Intent goToSignUpActivity = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(goToSignUpActivity);
        });
        logOutButton.setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    () -> Log.i(TAG, "Signed out successfully"),
                    error -> Log.e(TAG, error.toString())
            );
            Intent goToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goToLoginActivity);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                successResponse -> {
                    tasks.clear();
                    for (Task dataBaseTask : successResponse.getData()){
                        if(dataBaseTask.getTeam().getTeamName().equals(userTeam)) {
                            tasks.add(dataBaseTask);
                        }
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },
                failureResponse -> Log.i(Tag, "Did not read Tasks successfully")
        );

        username = preferences.getString(SettingsActivity.USER_NAME_TAG, "User");
        userTeam = preferences.getString(SettingsActivity.USER_TEAM_TAG, "Join a team to see your tasks");
        userTasksTV = (TextView) findViewById(R.id.textViewMainActivityUserTasks);
        userTeamTV = (TextView) findViewById(R.id.textViewMainActivityUserTeam);
        userTasksTV.setText(username + "'s Tasks:");
        userTeamTV.setText(userTeam);
    }
}