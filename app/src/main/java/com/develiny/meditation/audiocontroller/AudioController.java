package com.develiny.meditation.audiocontroller;

import android.content.Context;
import android.media.MediaPlayer;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;

public class AudioController {

    public static void startTrack(Context context, int page, int position) {

        MediaPlayer p1p1 = MediaPlayer.create(context, R.raw.p1p1);
        MediaPlayer p1p2 = MediaPlayer.create(context, R.raw.p1p2);
        MediaPlayer p2p1 = MediaPlayer.create(context, R.raw.p2p1);
        MediaPlayer p2p2 = MediaPlayer.create(context, R.raw.p2p2);

        if (page == 1) {
            if (position == 1) {
//                p1p1.start();
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
}
