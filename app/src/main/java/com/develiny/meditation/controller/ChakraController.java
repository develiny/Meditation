package com.develiny.meditation.controller;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ProgressBar;

import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.HzPage;

import java.io.IOException;

public class ChakraController {
    public static MediaPlayer mp;

    public static void startChakra(String pnp) {
        if (pnp.equals("3-1")) {
            ChakraPage.p3p1.start();
        } else if (pnp.equals("3-2")) {
            ChakraPage.p3p2.start();
        }
    }

    public static void stopChakra(int page, String pnp) {
        if (pnp.equals("3-1")) {
            ChakraPage.p3p1.stop();
            ChakraPage.p3p1.prepareAsync();
        }
    }
}
