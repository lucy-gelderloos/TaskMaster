package com.gelderloos.taskmaster.activities;

import static com.gelderloos.taskmaster.activities.AddTaskActivity.Tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskStatusEnum;
import com.amplifyframework.datastore.generated.model.Team;
import com.gelderloos.taskmaster.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String USER_NAME_TAG = "userName";
    public static final String USER_TEAM_TAG = "userTeam";

    List<String> teamNames = null;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString(USER_NAME_TAG, "");
        if(!userName.isEmpty()){
            EditText userNameEdited = ((EditText)findViewById(R.id.editTextSettingsEnterUsername));
            userNameEdited.setText(userName);
//            userNameEdited.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    userNameEdited.setText("");
//                }
//            });
        }

        teamNames = new ArrayList<>();

        setUpSubmitButton();
        setUpTeamSpinner();
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

    private void setUpSubmitButton(){
        Button submitButton = findViewById(R.id.buttonSettingsChangeUsername);
        Spinner userTeamSpinner = findViewById(R.id.spinnerSettingsTeam);

        submitButton.setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = preferences.edit();
            String nameInput = ((EditText) findViewById(R.id.editTextSettingsEnterUsername)).getText().toString();
            // save to sharedPref
            preferenceEditor.putString(USER_NAME_TAG, nameInput);
            String teamChoice = ((String) userTeamSpinner.getSelectedItem());
            preferenceEditor.putString(USER_TEAM_TAG, teamChoice);
            preferenceEditor.apply();
            Toast.makeText(SettingsActivity.this, "Settings saved", Toast.LENGTH_SHORT).show();
        });
    }

    private void setUpTeamSpinner(){
        Spinner userTeamSpinner = findViewById(R.id.spinnerSettingsTeam);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                teamNames);
        userTeamSpinner.setAdapter(adapter);
    }
}