package com.develiny.meditation.controller;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.HzPage;

import java.io.IOException;

public class HzController {
    public static MediaPlayer mp;

    public static void startHz(String pnp) {
        mp = getMediaPlayer(pnp);
        if (mp == null) {
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mp.setDataSource(getPath(pnp));
                mp.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.setLooping(true);
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mp.start();
                }
            });
        }
    }

    public static MediaPlayer getMediaPlayer(String pnp) {
        if (pnp.equals("4-1")) {
            return HzPage.p4p1;
        } else if (pnp.equals("4-2")) {
            return HzPage.p4p2;
        } else {
            return null;
        }
    }

    public static String getPath(String pnp) {
        if (pnp.equals("4-1")) {
            return "https://firebasestorage.googleapis.com/v0/b/meditation-7c5e1.appspot.com/o/Guns%20N_%20Roses%20-%20Welcome%20to%20the%20Jungle.mp3?alt=media&token=d58bb410-21eb-4ae3-8cad-8c86f93fd366";
        } else if (pnp.equals("4-2")) {
            return "https://firebasestorage.googleapis.com/v0/b/meditation-7c5e1.appspot.com/o/Guns%20_%20Roses%20-%20Knockin_%20On%20Heaven_s%20Door.mp3?alt=media&token=16630127-ed49-462e-a4e0-aa25423923bb";
        } else {
            return "";
        }
    }

    public static void stopHz(int page, String pnp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}