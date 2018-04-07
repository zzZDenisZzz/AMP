package com.kiev.corey.androidmediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_MOVIES;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим список
        lvMain = findViewById(R.id.lvMain);

        //находим директорию папки видео и добавляем название видео в ArrayList
        directory = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES);

        ArrayList<String> alPath = new ArrayList<>();
        ArrayList<String> alName = new ArrayList<>();

        File[] file = directory.listFiles();
        for (File aFile : file) {

            alName.add(aFile.getName());
            alPath.add(aFile.getAbsolutePath());

            // создаем адаптер присваиваем адаптер списку
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                    (this, android.R.layout.simple_list_item_1, alName);
            lvMain.setAdapter(arrayAdapter);
        }

        // создаем слушателя на нажатие видео
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = (String) lvMain.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, SelectedVideo.class);
                intent.putExtra("value", value);
//                intent.putExtra("directory", directory);
                startActivity(intent);

            }
        });
    }
}
