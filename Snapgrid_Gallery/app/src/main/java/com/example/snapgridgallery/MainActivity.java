package com.example.snapgridgallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PICK_FOLDER = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private Uri selectedFolderUri;
    private TextView tvFolderPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFolderPath = findViewById(R.id.tvFolderPath);
        Button btnPickFolder = findViewById(R.id.btnPickFolder);
        Button btnCapture = findViewById(R.id.btnCapture);
        Button btnGallery = findViewById(R.id.btnGallery);
        btnPickFolder.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent, REQUEST_PICK_FOLDER);
        });

        btnCapture.setOnClickListener(v -> {
            if (selectedFolderUri != null) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            } else {
                Toast.makeText(this, "Please choose a folder first!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGallery.setOnClickListener(v -> {
            if (selectedFolderUri != null) {
                Intent intent = new Intent(this, GalleryActivity.class);
                intent.putExtra("folderUri", selectedFolderUri.toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Choose a folder first!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_FOLDER && resultCode == Activity.RESULT_OK) {
            selectedFolderUri = data.getData();
            getContentResolver().takePersistableUriPermission(selectedFolderUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            tvFolderPath.setText("Selected Folder: " + selectedFolderUri.getPath());
        }

        else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            android.graphics.Bitmap imageBitmap = (android.graphics.Bitmap) extras.get("data");
            saveImageToFolder(imageBitmap);
        }
    }

    private void saveImageToFolder(android.graphics.Bitmap bitmap) {
        try {
            DocumentFile pickedDir = DocumentFile.fromTreeUri(this, selectedFolderUri);
            DocumentFile newFile = pickedDir.createFile("image/jpeg", "IMG_" + System.currentTimeMillis() + ".jpg");
            OutputStream out = getContentResolver().openOutputStream(newFile.getUri());
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
            Toast.makeText(this, "Photo Saved to Chosen Folder!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



