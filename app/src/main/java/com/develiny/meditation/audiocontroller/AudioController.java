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

    private static void checkOpenService(Context context) {//service 안켜져있으면 다시 시키
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

    private static MediaPlayer playingListindex0_1(String pp) {
        switch (pp) {
            case "1-1":
                return Page1.p1p1_1;
            case "1-2":
                return Page1.p1p2_1;
            case "2-1":
                return Page2.p2p2_1;
            case "2-2":
                return Page2.p2p2_1;
            default:
                return null;
        }
    }

    private static MediaPlayer playingListindex0_2(String pp) {
        switch (pp) {
            case "1-1":
                return Page1.p1p1_2;
            case "1-2":
                return Page1.p1p2_2;
            case "2-1":
                return Page2.p2p2_2;
            case "2-2":
                return Page2.p2p2_2;
            default:
                return null;
        }
    }
}
