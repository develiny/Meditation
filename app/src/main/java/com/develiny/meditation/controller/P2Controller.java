package com.develiny.meditation.controller;

import static com.develiny.meditation.page.Page2.p2p1_1;
import static com.develiny.meditation.page.Page2.p2p1_2;
import static com.develiny.meditation.page.Page2.p2p2_1;
import static com.develiny.meditation.page.Page2.p2p2_2;

import android.util.Log;

public class P2Controller {

    public static class p2p1t1 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p2p1_1.isPlaying()) {
                    int i = p2p1_1.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p2p1_2.start();
                        new p2p1t2().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }
    public static class p2p1t2 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p2p1_2.isPlaying()) {
                    int i = p2p1_2.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p2p1_1.start();
                        new p2p1t1().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }

    public static class p2p2t1 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p2p2_1.isPlaying()) {
                    int i = p2p2_1.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p2p2_2.start();
                        new p2p2t2().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }
    public static class p2p2t2 extends Thread {
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p2p2_2.isPlaying()) {
                    int i = p2p2_2.getCurrentPosition();
                    if (i >= 69000) {//01:13:01 / 01:09:00
                        p2p2_1.start();
                        new p2p2t1().start();
                        setStop(true);
                        Log.d("<<<", "re");
                    }
                }
            }
        }
    }

    public static void stopPage2() {
        p2p1_1.stop();
        p2p1_1.prepareAsync();
        p2p1_2.stop();
        p2p1_2.prepareAsync();
        p2p2_1.stop();
        p2p2_1.prepareAsync();
        p2p2_2.stop();
        p2p2_2.prepareAsync();
        new p2p1t1().setStop(true);
        new p2p1t2().setStop(true);
        new p2p2t1().setStop(true);
        new p2p2t2().setStop(true);
    }
}
