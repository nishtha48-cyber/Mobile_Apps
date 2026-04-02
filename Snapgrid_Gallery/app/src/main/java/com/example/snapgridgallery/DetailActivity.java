package com.example.snapgridgallery;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class DetailActivity extends AppCompatActivity {

    File currentFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView ivFullImage = findViewById(R.id.ivFullImage);
        TextView tvDetails = findViewById(R.id.tvDetails);

        String path = getIntent().getStringExtra("imagePath");
        if (path != null) {
            currentFile = new File(path);
            ivFullImage.setImageURI(Uri.fromFile(currentFile));
            String name = "Name: " + currentFile.getName();
            String location = "\nPath: " + currentFile.getAbsolutePath();
            String size = "\nSize: " + (currentFile.length() / 1024) + " KB";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            String date = "\nDate: " + sdf.format(new Date(currentFile.lastModified()));
            tvDetails.setText(name + location + size + date);
        }

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            showConfirmDialog();
        });
    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this image permanently?")
                .setPositiveButton("Yes, Delete", (dialog, which) -> {
                    if (currentFile.delete()) {
                        Toast.makeText(this, "Image Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Error deleting file", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    }


