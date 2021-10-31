package com.develiny.meditation.audiocontroller;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;

import java.util.List;

public class AudioController {

    public static void startTrack(Context context, int page, int position) {

        if (page == 1) {//page1
            if (position == 1) {
                Page1.p1p1_1.start();
                new P1Controller.p1p1t1().start();
            } else if (position == 2) {
                Page1.p1p2_1.start();
                new P1Controller.p1p2t1().start();
            }
        } else if (page == 2) {//page2
            if (position == 1) {
                Page2.p2p1_1.start();
                new P2Controller.p2p1t1().start();
            } else if (position == 2) {
                Page2.p2p2_1.start();
                new P2Controller.p2p2t1().start();
            }
        }
    }

    public static void startPlayingList(Context context, List<String> pp) {//playingList에 있는 곡만 다시 재생
        for (int i = 0; i < pp.size(); i++) {
            checkPP(context, pp.get(i));
            checkOpenService(context);
        }
    }

    public static void checkPP(Context context, String pp) {//곡 찾아서 재생

        Intent intent = new Intent(context, NotificationService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
        switch (pp) {
            case "1-1":
                Page1.p1p1_1.start();
                new P1Controller.p1p1t1().start();
                break;
            case "1-2":
                Page1.p1p2_1.start();
                new P1Controller.p1p2t1().start();
                break;
            case "2-1":
                Page2.p2p1_1.start();
                new P2Controller.p2p1t1().start();
                break;
            case "2-2":
                Page2.p2p2_1.start();
                new P2Controller.p2p2t1().start();
                break;
        }
    }

    public static void checkOpenService(Context context) {//service 안켜져있으면 다시 시키
        if (!NotificationService.isPlaying) {
            Intent intent = new Intent(context, NotificationService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }

    public static boolean checkIsPlaying(String pp) {
        return playingListindex0_1(pp).isPlaying() || playingListindex0_2(pp).isPlaying();
    }

    public static MediaPlayer playingListindex0_1(String pp) {
        switch (pp) {
            case "1-1":
                return Page1.p1p1_1;
            case "1-2":
                return Page1.p1p2_1;
            case "2-1":
                return Page2.p2p1_1;
            case "2-2":
                return Page2.p2p2_1;
            default:
                return null;
        }
    }

    public static MediaPlayer playingListindex0_2(String pp) {
        switch (pp) {
            case "1-1":
                return Page1.p1p1_2;
            case "1-2":
                return Page1.p1p2_2;
            case "2-1":
                return Page2.p2p1_2;
            case "2-2":
                return Page2.p2p2_2;
            default:
                return null;
        }
    }

    public static void stopPage(int page) {
        if (page == 1) {
            P1Controller.stopPage1();
        } else if (page == 2) {
            P2Controller.stopPage2();
        }
    }

    public static void setVolumn(String pp, int i) {
        float float0 = (float) 0.0;
        float float1 = (float) 0.023277352;
        float float2 = (float) 0.04816127;
        float float3 = (float) 0.07489007;
        float float4 = (float) 0.10375938;
        float float5 = (float) 0.13514209;
        float float6 = (float) 0.16951798;
        float float7 = (float) 0.20751876;
        float float8 = (float) 0.25;
        float float9 = (float) 0.29816127;
        float float10 = (float) 0.35375938;
        float float11 = (float) 0.41951796;
        float float12 = (float) 0.5;
        float float13 = (float) 0.60375935;
        float float14 = (float) 0.75;
        float float15 = (float) 1.0;
        if (i == 0) {
            AudioController.playingListindex0_1(pp).setVolume(float0, float0);
            AudioController.playingListindex0_2(pp).setVolume(float0, float0);
        } else if (i == 1) {
            AudioController.playingListindex0_1(pp).setVolume(float1, float1);
            AudioController.playingListindex0_2(pp).setVolume(float1, float1);
        } else if (i == 2) {
            AudioController.playingListindex0_1(pp).setVolume(float2, float2);
            AudioController.playingListindex0_2(pp).setVolume(float2, float2);
        } else if (i == 3) {
            AudioController.playingListindex0_1(pp).setVolume(float3, float3);
            AudioController.playingListindex0_2(pp).setVolume(float3, float3);
        } else if (i == 4) {
            AudioController.playingListindex0_1(pp).setVolume(float4, float4);
            AudioController.playingListindex0_2(pp).setVolume(float4, float4);
        } else if (i == 5) {
            AudioController.playingListindex0_1(pp).setVolume(float5, float5);
            AudioController.playingListindex0_2(pp).setVolume(float5, float5);
        } else if (i == 6) {
            AudioController.playingListindex0_1(pp).setVolume(float6, float6);
            AudioController.playingListindex0_2(pp).setVolume(float6, float6);
        } else if (i == 7) {
            AudioController.playingListindex0_1(pp).setVolume(float7, float7);
            AudioController.playingListindex0_2(pp).setVolume(float7, float7);
        } else if (i == 8) {
            AudioController.playingListindex0_1(pp).setVolume(float8, float8);
            AudioController.playingListindex0_2(pp).setVolume(float8, float8);
        } else if (i == 9) {
            AudioController.playingListindex0_1(pp).setVolume(float9, float9);
            AudioController.playingListindex0_2(pp).setVolume(float9, float9);
        } else if (i == 10) {
            AudioController.playingListindex0_1(pp).setVolume(float10, float10);
            AudioController.playingListindex0_2(pp).setVolume(float10, float10);
        } else if (i == 11) {
            AudioController.playingListindex0_1(pp).setVolume(float11, float11);
            AudioController.playingListindex0_2(pp).setVolume(float11, float11);
        } else if (i == 12) {
            AudioController.playingListindex0_1(pp).setVolume(float12, float12);
            AudioController.playingListindex0_2(pp).setVolume(float12, float12);
        } else if (i == 13) {
            AudioController.playingListindex0_1(pp).setVolume(float13, float13);
            AudioController.playingListindex0_2(pp).setVolume(float13, float13);
        } else if (i == 14) {
            AudioController.playingListindex0_1(pp).setVolume(float14, float14);
            AudioController.playingListindex0_2(pp).setVolume(float14, float14);
        } else if (i == 15) {
            AudioController.playingListindex0_1(pp).setVolume(float15, float15);
            AudioController.playingListindex0_2(pp).setVolume(float15, float15);
        }
    }
}
