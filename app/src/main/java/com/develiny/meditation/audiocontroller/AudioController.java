package com.develiny.meditation.audiocontroller;

import android.content.Context;
import android.media.MediaPlayer;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;

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

    boolean isNull () {
        if (MainActivity.playingList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void openNotification(Context context, int page, int position) {

    }
}
