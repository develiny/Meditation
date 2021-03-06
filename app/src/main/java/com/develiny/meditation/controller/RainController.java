package com.develiny.meditation.controller;

import static com.develiny.meditation.page.RainPage.p1p1_1;
import static com.develiny.meditation.page.RainPage.p1p1_2;
import static com.develiny.meditation.page.RainPage.p1p2_1;
import static com.develiny.meditation.page.RainPage.p1p2_2;

import android.util.Log;

public class RainController {

    public static class p1t1 extends Thread {
        String pnp;

        public p1t1(String pnp) {
            this.pnp = pnp;
        }
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p1p1_1.isPlaying()) {
                    int i = p1p1_1.getCurrentPosition();
                    if (i >= getSec(pnp)) {
                        p1p1_2.start();
                        new p1t2(pnp).start();
                        setStop(true);
                    }
                }
            }
        }
    }
    public static class p1t2 extends Thread {
        String pnp;
        public p1t2(String pnp) {
            this.pnp = pnp;
        }
        private boolean stop;
        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                if (p1p1_2.isPlaying()) {
                    int i = p1p1_2.getCurrentPosition();
                    if (i >= getSec(pnp)) {
                        p1p1_1.start();
                        new p1t1(pnp).start();
                        setStop(true);
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
        new p1t1(null).setStop(true);
        new p1t2(null).setStop(true);
    }

    private static int getSec(String pnp) {
        if (pnp.equals("1-1")) {
            return 69000;
        } else if (pnp.equals("1-2")) {
            return 69000;
        } else {
            return 0;
        }
    }
}
