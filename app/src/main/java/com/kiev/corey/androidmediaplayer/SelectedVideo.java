package com.kiev.corey.androidmediaplayer;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

import static android.os.Environment.DIRECTORY_MOVIES;

public class SelectedVideo extends AppCompatActivity {

    VideoView videoView;
    Button stop, play;
    String itemValue;
    File directory;
    MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        videoView = findViewById(R.id.video_View);
        stop = findViewById(R.id.btn_stop);
        play = findViewById(R.id.btn_play);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            itemValue = bundle.getString("value");
        }
        directory = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        mediaController.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO next button clicked
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mediaController.show(10000);


        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                videoView.stopPlayback();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse(directory + "/" + itemValue);
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });
    }


    // Изменение конфигурации для "ORIENTATION LANDSCAPE"
    LinearLayout.LayoutParams paramsNotFullscreen; // если использую LinearLayout

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) //В полноэкранном режиме
        {
            paramsNotFullscreen=(LinearLayout.LayoutParams)videoView.getLayoutParams();
            LinearLayout.LayoutParams params= null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                params = new LinearLayout.LayoutParams(paramsNotFullscreen);
            }
            if (params != null) {
                params.setMargins(0, 0, 0, 0);
                params.height=ViewGroup.LayoutParams.MATCH_PARENT;
                params.width=ViewGroup.LayoutParams.MATCH_PARENT;
            }

            videoView.setLayoutParams(params);

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            videoView.setLayoutParams(paramsNotFullscreen);
        }
    }
}
