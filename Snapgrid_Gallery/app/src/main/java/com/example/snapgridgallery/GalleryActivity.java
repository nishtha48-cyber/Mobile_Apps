package com.example.snapgridgallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    List<DocumentFile> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        String uriString = getIntent().getStringExtra("folderUri");
        if (uriString != null) {
            Uri folderUri = Uri.parse(uriString);
            DocumentFile pickedDir = DocumentFile.fromTreeUri(this, folderUri);
            for (DocumentFile file : pickedDir.listFiles()) {
                if (file.getType() != null && file.getType().startsWith("image/")) {
                    imageList.add(file);
                }
            }
            recyclerView.setAdapter(new GalleryAdapter());
        }
    }
    private class GalleryAdapter extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            DocumentFile file = imageList.get(position);
            holder.img.setImageURI(file.getUri());
            holder.itemView.setOnClickListener(v -> {
                Intent i = new Intent(GalleryActivity.this, DetailActivity.class);
                i.putExtra("fileUri", file.getUri().toString());
                startActivity(i);
            });
        }

        @Override
        public int getItemCount() { return imageList.size(); }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ViewHolder(View v) { super(v); img = v.findViewById(R.id.ivGalleryItem); }
    }
}



