package com.gelderloos.taskmaster.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.gelderloos.taskmaster.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageTaskActivity extends AppCompatActivity {

    public static final String TAG = "ImageTaskActivity";
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String s3ImageKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_task);

        activityResultLauncher = getImagePickingActivityResultLauncher();

        setUpAddImageButton();
        setUpSaveButton();
    }

    private void setUpSaveButton(){
        findViewById(R.id.ButtonImageTaskSave).setOnClickListener(view -> {
            Intent goToAddTask = new Intent(ImageTaskActivity.this, AddTaskActivity.class);
            goToAddTask.putExtra("S3ImageKey",s3ImageKey);
            startActivity(goToAddTask);
        });
    }

    private void setUpAddImageButton(){
        findViewById(R.id.ButtonImageTaskAdd).setOnClickListener(b -> {
            launchImageSelectionIntent();
        });
    }

    private void launchImageSelectionIntent(){
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageFilePickingIntent.setType("*/*");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});
        activityResultLauncher.launch(imageFilePickingIntent);
    }

    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher(){
        ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
            registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Uri pickedImageFileUri = result.getData().getData();
                        try {
                            InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                            String pickedImageFilename = getFileNameFromUri(pickedImageFileUri);
                            uploadInputStreamToS3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
                        } catch (FileNotFoundException fnfe) {
                            Log.e(TAG, "Could not get file from file picker! " + fnfe.getMessage(), fnfe);
                        }
                    }
                }
            );
        return imagePickingActivityResultLauncher;
    }

    private void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFileName, Uri pickedImageFileUri){
        Amplify.Storage.uploadInputStream(
            pickedImageFileName,
            pickedImageInputStream,
            success -> {
                Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
                s3ImageKey = success.getKey();
                ImageView taskImageView = findViewById(R.id.imageViewImageTaskImage);
                InputStream pickedImageInputStreamCopy = null;
                try {
                    pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
                } catch (FileNotFoundException fnfe) {
                    Log.e(TAG, "Could not get file stream from URI! " + fnfe.getMessage(), fnfe);
                }
                taskImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
            },
            failure -> Log.e(TAG, "Failure in uploading file to S3 with filename: " + pickedImageFileName + " with error: " + failure.getMessage())
        );

    }

    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
