package com.gelderloos.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gelderloos.taskmaster.R;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String USER_NAME_TAG = "userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString(USER_NAME_TAG, "");
        if(!userName.isEmpty()){
            EditText userNameEdited = ((EditText)findViewById(R.id.editTextSettingsEnterUsername));
            userNameEdited.setText(userName);
            userNameEdited.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    userNameEdited.setText("");
                }
            });
        }
        setUpSubmitButton();
    }

    private void setUpSubmitButton(){
        Button submitButton = findViewById(R.id.buttonSettingsChangeUsername);
        submitButton.setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = preferences.edit();
            String nameInput = ((EditText) findViewById(R.id.editTextSettingsEnterUsername)).getText().toString();
            // save to sharedPref
            preferenceEditor.putString(USER_NAME_TAG, nameInput);
            preferenceEditor.apply();
            Toast.makeText(SettingsActivity.this, "Settings saved", Toast.LENGTH_SHORT).show();
        });
    }
}