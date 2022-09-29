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
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.*;
import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.adapters.TaskListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "task_list_db";
    private static final String TAG = "MainActivity";
    SharedPreferences preferences;
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

        setUpTaskListRecyclerView();
        setUpAddTaskButton();
        setUpAllTasksButton();
        setUpSettingsButton();

        //         Hardcoding Teams

//        Team newTeamJava = Team.builder()
//                .teamName("Team Java")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(newTeamJava),
//                success -> Log.i(TAG, "Team added"),
//                failure -> Log.i(TAG, "Didn't work")
//        );
//
//        Team newTeamHTML = Team.builder()
//                .teamName("HTML Team")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(newTeamHTML),
//                success -> Log.i(TAG, "Team added"),
//                failure -> Log.i(TAG, "Team not added")
//        );
//
//        Team newTeamCSS = Team.builder()
//                .teamName("CSS Team")
//                .build();
//        Amplify.API.mutate(
//                ModelMutation.create(newTeamCSS),
//                success -> Log.i(TAG, "Team added"),
//                failure -> Log.i(TAG, "Team not added")
//        );
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
        RecyclerView taskListRecyclerView = findViewById(R.id.recyclerMainActivityTaskRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskListRecyclerView.setLayoutManager(layoutManager);
        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Amplify.API.query(
                // list gives ALL items, get() gives you 1
                ModelQuery.list(Task.class),
                successResponse -> {
                    Log.i(Tag, "Tasks read successfully!");
                    tasks.clear();
                    for (Task dataBaseTask : successResponse.getData()){
                        if(dataBaseTask.getTeam().getTeamName().equals(userTeam)) {
                            tasks.add(dataBaseTask);
                        }
//                        tasks.add(dataBaseTask);

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
        // TODO: convert below string to resource string
        userTasksTV.setText(username + "'s Tasks:");
        userTeamTV.setText(userTeam);
    }
}