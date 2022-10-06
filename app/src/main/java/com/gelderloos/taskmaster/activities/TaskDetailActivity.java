package com.gelderloos.taskmaster.activities;

import static android.content.ContentValues.TAG;
import static com.gelderloos.taskmaster.activities.AddTaskActivity.Tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.services.s3.model.Tag;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;
import com.gelderloos.taskmaster.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String TASK_TITLE_EXTRA_TAG = "taskTitle";
    public static final String TASK_BODY_EXTRA_TAG = "taskBody";
    public static final String TASK_STATE_EXTRA_TAG = "taskState";
    public static final String TASK_IMAGE_EXTRA_TAG = "taskImageS3Key";
    public static final String TASK_LATITUDE_EXTRA_TAG = "taskLatitude";
    public static final String TASK_LONGITUDE_EXTRA_TAG = "taskLongitude";

    String imageS3Key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("taskTitle");
        TextView titleText = findViewById(R.id.textViewTaskDetailTitle);
        titleText.setText(title);
        String body = intent.getStringExtra("taskBody");
        TextView bodyText = findViewById(R.id.textViewTaskDetailBody);
        bodyText.setText(body);
        String state = intent.getStringExtra("taskState");
        TextView taskState = findViewById(R.id.textViewTaskDetailState);
        taskState.setText("Status: " + state);
        String locationString = "Lat: " + intent.getStringExtra("taskLatitude") + ", Lon: " + intent.getStringExtra("taskLongitude");
        TextView taskLocation = findViewById(R.id.textViewTaskDetailLocation);
        taskLocation.setText(locationString);


        imageS3Key = intent.getStringExtra("taskImageS3Key");

        setUpTaskImageView();
    }

    public void setUpTaskImageView() {
        ImageView taskImage = findViewById(R.id.imageViewTaskDetailImage);
        Amplify.Storage.downloadFile(
                imageS3Key,
                // TODO can handle .png
                new File(getApplicationContext().getFilesDir() + "/image.jpg"),
                StorageDownloadFileOptions.defaultInstance(),
                progress -> Log.i(TAG, "Fraction completed: " + progress.getFractionCompleted()),
                result -> Log.i(TAG, "Successfully downloaded: " + result.getFile().getName()),
                error -> Log.e(TAG,  "Download Failure", error));
        // TODO image displays on page
    }
}