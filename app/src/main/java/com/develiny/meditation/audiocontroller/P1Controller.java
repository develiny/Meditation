package com.develiny.meditation.audiocontroller;

import static com.develiny.meditation.page.Page1.p1p1_1;
import static com.develiny.meditation.page.Page1.p1p1_2;
import static com.develiny.meditation.page.Page1.p1p2_1;
import static com.develiny.meditation.page.Page1.p1p2_2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.develiny.meditation.R;

public class P1Controller {

    public static class p1p1t1 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p1p1_1.isPlaying()) {
                    int i = p1p1_1.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p1p1_2.start();
                        new p1p1t2().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }
    public static class p1p1t2 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p1p1_2.isPlaying()) {
                    int i = p1p1_2.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p1p1_1.start();
                        new p1p1t1().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }

    public static class p1p2t1 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p1p2_1.isPlaying()) {
                    int i = p1p2_1.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p1p2_2.start();
                        new p1p2t2().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }
    public static class p1p2t2 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p1p2_2.isPlaying()) {
                    int i = p1p2_2.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p1p2_1.start();
                        new p1p2t1().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }

    public static void stopPage1() {
        p1p1_1.stop();
        p1p1_1.prepareAsync();
        p1p1_2.stop();
        p1p1_2.prepareAsync();
        p1p2_1.stop();
        p1p2_1.prepareAsync();
        p1p2_2.stop();
        p1p2_2.prepareAsync();
        new p1p1t1().setStop(true);
        new p1p1t2().setStop(true);
        new p1p2t1().setStop(true);
        new p1p2t2().setStop(true);
    }
}
