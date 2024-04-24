package com.app.ecommerceapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sample);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            // Save image to external storage
            String fileName = "my_image.png"; // Name for the image file
            boolean success = saveImageToExternalStorage(getApplicationContext(), R.drawable.india, fileName);
            if (success) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Save image to external storage
                String fileName = "my_image.png"; // Name for the image file
                boolean success = saveImageToExternalStorage(getApplicationContext(), R.drawable.india, fileName);
                if (success) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permission is Required", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public static boolean saveImageToExternalStorage(Context context, int imageResourceId, String fileName) {
        // Check if external storage is available and writable
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            // External storage is either not available or not writable
            return false;
        }

        // Get the directory for the user's public pictures directory.
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        // Create the storage directory if it does not exist
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                // Failed to create directory
                return false;
            }
        }

        // Create a file to save the image
        File imageFile = new File(directory, fileName);

        // Load the image from resources
        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(), imageResourceId);

        try {
            // Write the bitmap to the file
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}