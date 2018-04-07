package com.kiev.corey.androidmediaplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

import static android.os.Environment.DIRECTORY_MOVIES;

public class SelectedVideo extends AppCompatActivity {

    VideoView videoView;
    Button play;
    String itemValue;
    File directory;
    MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        videoView = findViewById(R.id.video_View);
        play = findViewById(R.id.btn_play);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            itemValue = bundle.getString("value");
        }
        directory = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES);

        mediaController = new MediaController(this);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse(directory+"/" + itemValue);
                videoView.setVideoURI(uri);
                videoView.setMediaController(mediaController);
                videoView.start();
            }
        });


    }
}
