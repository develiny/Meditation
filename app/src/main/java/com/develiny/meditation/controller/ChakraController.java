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
        ChakraPage.load.setVisibility(View.VISIBLE);
        mp = getMediaPlayer(pnp);
        if (mp == null) {
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            setVolumn(pnp);
            try {
                mp.setDataSource(getPath(pnp));
                mp.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
                ChakraPage.load.setVisibility(View.GONE);
            }
            mp.setLooping(true);
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mp.start();
                    ChakraPage.load.setVisibility(View.GONE);
                }
            });
        }
    }

    public static MediaPlayer getMediaPlayer(String pnp) {
        if (pnp.equals("3-1")) {
            return ChakraPage.p3p1;
        } else if (pnp.equals("3-2")) {
            return ChakraPage.p3p2;
        } else {
            return null;
        }
    }

    public static String getPath(String pnp) {
        if (pnp.equals("3-1")) {
            return "https://firebasestorage.googleapis.com/v0/b/meditation-7c5e1.appspot.com/o/cde.wav?alt=media&token=19945e3d-2ac4-4b3b-8a98-8930c8874a03";
        } else if (pnp.equals("3-2")) {
            return "https://firebasestorage.googleapis.com/v0/b/meditation-7c5e1.appspot.com/o/Guns%20N_%20Roses%20-%20Paradise%20City.mp3?alt=media&token=f6107a48-d0d2-4a9c-bdfa-f6d73096896b";
        } else {
            return "";
        }
    }

    public static void stopChakra(int page, String pnp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public static void setVolumn(String pnp) {
        for (int i = 0; i < ChakraPage.arrayList.size(); i++) {
            if (ChakraPage.arrayList.get(i).getPnp().equals(pnp)) {
                AudioController.setVolumn(pnp, ChakraPage.arrayList.get(i).getSeek());
            }
        }
    }
}
