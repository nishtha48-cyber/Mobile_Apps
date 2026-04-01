package com.example.streaming;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;
import android.widget.Toast;
import android.media.MediaPlayer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    EditText etUrl;
    MediaPlayer mediaPlayer;
    Uri audioUri, videoUri;
    boolean isVideoActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        videoView.setZOrderOnTop(true);
        etUrl = findViewById(R.id.etUrl);

        findViewById(R.id.btnOpenFile).setOnClickListener(v -> openFilePicker());
        findViewById(R.id.btnOpenUrl).setOnClickListener(v -> streamVideo());
        findViewById(R.id.btnPlay).setOnClickListener(v -> playMedia());
        findViewById(R.id.btnPause).setOnClickListener(v -> pauseMedia());
        findViewById(R.id.btnStop).setOnClickListener(v -> stopMedia());
        findViewById(R.id.btnRestart).setOnClickListener(v -> restartMedia());
    }
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, 101);
    }

    private void streamVideo() {
        String url = etUrl.getText().toString().trim();
        if (url.isEmpty()) {
            Toast.makeText(this, "Please enter URL", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            isVideoActive = true;
            if (mediaPlayer != null) mediaPlayer.stop();
            android.widget.MediaController mediaController = new android.widget.MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            videoUri = Uri.parse(url);
            videoView.setVideoURI(videoUri);
            videoView.setOnPreparedListener(mp -> {
                Toast.makeText(MainActivity.this, "Streaming Started", Toast.LENGTH_SHORT).show();
                videoView.start();
            });

            videoView.setOnErrorListener((mp, what, extra) -> {
                Toast.makeText(MainActivity.this, "Error: Check URL or Internet", Toast.LENGTH_SHORT).show();
                return true;
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            audioUri = data.getData();
            isVideoActive = false;
            videoView.stopPlayback();

            if (mediaPlayer != null) mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, audioUri);
            Toast.makeText(this, "Audio File Loaded", Toast.LENGTH_SHORT).show();
        }
    }
    private void playMedia() {
        if (isVideoActive) videoView.start();
        else if (mediaPlayer != null) mediaPlayer.start();
    }

    private void pauseMedia() {
        if (isVideoActive) videoView.pause();
        else if (mediaPlayer != null) mediaPlayer.pause();
    }
    private void stopMedia() {
        if (isVideoActive) videoView.stopPlayback();
        else if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, audioUri); // Reset to allow play again
        }
    }
    private void restartMedia() {
        if (isVideoActive) {
            videoView.resume();
            videoView.start();
        } else if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }
}