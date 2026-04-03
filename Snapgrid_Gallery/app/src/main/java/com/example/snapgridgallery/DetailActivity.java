package com.example.snapgridgallery;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button btnBack = findViewById(R.id.btnBackToGallery);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.btnBackToGallery).setOnClickListener(v -> {
            finish();
        });

        ImageView ivFull = findViewById(R.id.ivFullImage);
        TextView tvDetails = findViewById(R.id.tvDetails);
        Uri fileUri = Uri.parse(getIntent().getStringExtra("fileUri"));
        DocumentFile doc = DocumentFile.fromSingleUri(this, fileUri);
        ivFull.setImageURI(fileUri);

        if (doc != null) {
            String name = "Name: " + doc.getName();
            String path = "\nPath: " + fileUri.getPath();
            String size = "\nSize: " + (doc.length() / 1024) + " KB";
            String date = "\nDate: " + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(doc.lastModified()));
            tvDetails.setText(name + path + size + date);
        }

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Delete")
                    .setMessage("Delete this image forever?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        if (doc.delete()) {
                            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", null).show();
        });
    }
}

//File currentFile;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        ImageView ivFullImage = findViewById(R.id.ivFullImage);
//        TextView tvDetails = findViewById(R.id.tvDetails);
//        MaterialButton btnDelete = findViewById(R.id.btnDelete);
//        String path = getIntent().getStringExtra("path");
//        if (path != null) {
//            currentFile = new File(path);
//            ivFullImage.setImageURI(Uri.fromFile(currentFile));
//            String name = "Name: " + currentFile.getName();
//            String location = "\nPath: " + currentFile.getAbsolutePath();
//            String size = "\nSize: " + (currentFile.length() / 1024) + " KB";
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
//            String date = "\nDate Taken: " + sdf.format(new Date(currentFile.lastModified()));
//
//            tvDetails.setText(name + location + size + date);
//        }
//
//        btnDelete.setOnClickListener(v -> {
//            confirmDelete();
//        });
//    }
//
//    private void confirmDelete() {
//        new AlertDialog.Builder(this)
//                .setTitle("Confirm Delete")
//                .setMessage("Are you sure you want to delete this image permanently?")
//                .setPositiveButton("Delete", (dialog, which) -> {
//                    if (currentFile != null && currentFile.exists()) {
//                        if (currentFile.delete()) {
//                            Toast.makeText(this, "Image Deleted Successfully", Toast.LENGTH_SHORT).show();
//                            finish(); // Returns the user to the Image Gallery view
//                        } else {
//                            Toast.makeText(this, "Error: Could not delete file", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
//                .show();
//    }
//}
//
//
