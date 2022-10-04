package com.gelderloos.taskmaster.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.gelderloos.taskmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpLoginForm();
    }

    private void setUpLoginForm() {
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(VerifyActivity.VERIFY_ACCOUNT_EMAIL_TAG);
        EditText emailET = findViewById(R.id.editTextLoginEmail);
        emailET.setText(userEmail);
        findViewById(R.id.buttonLoginActivityLogIn).setOnClickListener(view -> {
            String userSelectedEmail = emailET.getText().toString();
            String userPassword = ((EditText) findViewById(R.id.editTextLoginPassword)).getText().toString();

            Amplify.Auth.signIn(
                    userSelectedEmail,
                    userPassword,
                    success -> {
                        Log.i(TAG, "Login Succeeded: " + success);
                        Intent goToMainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(goToMainIntent);
                    },
                    failure -> {
                        Log.i(TAG, "Login failed: " + failure);
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Login failed!",Toast.LENGTH_SHORT).show());
                    }
            );
        });
    }
}