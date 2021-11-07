package com.develiny.meditation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        String path = getApplicationInfo().dataDir;
        File file = new File(path + "/audio");
        if (file.exists()) {

        } else {

        }

        startActivity(new Intent(Splash.this, MainActivity.class));

    }
}
