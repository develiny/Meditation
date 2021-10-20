package com.develiny.meditation.audiocontroller;

import android.content.Context;
import android.media.MediaPlayer;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;

public class AudioController {

    public static void startTrack(Context context, int page, int position) {

        if (page == 1) {
            if (position == 1) {
                Page1.p1p1_1.start();
                new P1Controller.p1p1t1().start();
            } else if (position == 2) {

            }
        } else if (page == 2) {
            if (position == 1) {

            } else if (position == 2) {

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
