package com.develiny.meditation.controller;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;

import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.HzPage;

import java.io.IOException;

public class HzController {
    public static MediaPlayer mp;

    public static void startHz(String pnp) {
        if (pnp.equals("4-1")) {
            HzPage.p4p1.start();
        } else if (pnp.equals("4-2")) {
            HzPage.p4p2.start();
        }
    }

    public static void stopHz(int page, String pnp) {
        if (pnp.equals("4-1")) {
            if (HzPage.p4p1 != null) {
                HzPage.p4p1.stop();
                HzPage.p4p1.prepareAsync();
            }
        } else if (pnp.equals("4-2")) {
            if (HzPage.p4p2 != null) {
                HzPage.p4p2.stop();
                HzPage.p4p2.prepareAsync();
            }
        }
    }
}