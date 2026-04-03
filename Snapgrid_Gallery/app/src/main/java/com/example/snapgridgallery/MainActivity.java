package com.example.snapgridgallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    private static final int REQ_SAVE_FOLDER = 200;
    private static final int REQ_VIEW_FOLDER = 300;
    private static final int REQ_CAPTURE = 101;

    private Uri saveFolderUri;
    private TextView tvFolderPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFolderPath = findViewById(R.id.tvFolderPath);
        Button btnPickSave = findViewById(R.id.btnPickFolder);
        Button btnCapture = findViewById(R.id.btnCapture);
        Button btnViewPhotos = findViewById(R.id.btnGallery);

        btnPickSave.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent, REQ_SAVE_FOLDER);
        });

        btnCapture.setOnClickListener(v -> {
            if (saveFolderUri != null) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQ_CAPTURE);
            } else {
                Toast.makeText(this, "Choose a SAVE folder first!", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewPhotos.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent, REQ_VIEW_FOLDER);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == REQ_SAVE_FOLDER) {
            saveFolderUri = data.getData();
            getContentResolver().takePersistableUriPermission(saveFolderUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            tvFolderPath.setText("Save Location: " + saveFolderUri.getPath());
        } else if (requestCode == REQ_VIEW_FOLDER) {
            Uri viewFolderUri = data.getData();
            Intent intent = new Intent(this, GalleryActivity.class);
            intent.putExtra("folderUri", viewFolderUri.toString());
            startActivity(intent);
        } else if (requestCode == REQ_CAPTURE) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            saveImage(imageBitmap);
        }
    }

    private void saveImage(Bitmap bitmap) {
        try {
            DocumentFile pickedDir = DocumentFile.fromTreeUri(this, saveFolderUri);
            DocumentFile newFile = pickedDir.createFile("image/jpeg", "IMG_" + System.currentTimeMillis());
            OutputStream out = getContentResolver().openOutputStream(newFile.getUri());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



