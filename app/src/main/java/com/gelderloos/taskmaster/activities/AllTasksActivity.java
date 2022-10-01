package com.gelderloos.taskmaster.activities;

import static com.gelderloos.taskmaster.activities.AddTaskActivity.Tag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.adapters.TaskListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllTasksActivity extends AppCompatActivity {

    SharedPreferences preferences;
    String userTeam;
    String username;

    List<Task> tasks = null;
    TaskListRecyclerViewAdapter adapter;
    TextView userTasksTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        tasks = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpTaskListRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                successResponse -> {
                    Log.i(Tag, "Tasks read successfully!");
                    tasks.clear();
                    for (Task dataBaseTask : successResponse.getData()) {
                            tasks.add(dataBaseTask);
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },
                failureResponse -> Log.i(Tag, "Did not read Tasks successfully")
        );

        username = preferences.getString(SettingsActivity.USER_NAME_TAG, "User");
        userTasksTV = (TextView) findViewById(R.id.textViewAllTasksActivityUserTasks);
        userTasksTV.setText(username + "'s Tasks:");
    }

        private void setUpTaskListRecyclerView() {
            RecyclerView taskListRecyclerView = findViewById(R.id.recyclerAllTasksActivityTaskRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            taskListRecyclerView.setLayoutManager(layoutManager);
            adapter = new TaskListRecyclerViewAdapter(tasks, this);
            taskListRecyclerView.setAdapter(adapter);
        }

}