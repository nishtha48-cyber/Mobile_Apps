package com.example.snapgridgallery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<File> imageFiles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        File folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (folder != null && folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                imageFiles = Arrays.asList(files);
            }
        }
        ImageAdapter adapter = new ImageAdapter(imageFiles);
        recyclerView.setAdapter(adapter);
    }

    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
        List<File> files;
        ImageAdapter(List<File> files) { this.files = files; }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            File currentFile = files.get(position);
            holder.imageView.setImageURI(android.net.Uri.fromFile(currentFile));
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(GalleryActivity.this, DetailActivity.class);
                intent.putExtra("imagePath", currentFile.getAbsolutePath());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() { return files.size(); }
        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.ivGalleryItem);
            }
        }
    }
}







