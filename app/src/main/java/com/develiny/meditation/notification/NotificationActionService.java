package com.develiny.meditation.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.controller.RainController;
import com.develiny.meditation.controller.WindController;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;
import java.util.List;

public class NotificationActionService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ac = intent.getAction();
        switch (ac) {
            case DefaultNofitication.ACTION_PLAY:
                checkOpenService(context);
                if (AudioController.checkIsPlaying(MainActivity.playingList.get(0).getPnp())) {//재생중
                    MainActivity.pands.setBackgroundResource(R.drawable.bottom_play);
                    ArrayList<PageItem> page = new ArrayList<>();
                    for (int i = 0; i < MainActivity.playingList.size(); i++) {
                        page.add(MainActivity.playingList.get(i));
                        if (i == MainActivity.playingList.size() - 1) {
                            AudioController.stopPlayingList(page);
                            DefaultNofitication.defauleNotification(context);
                        }
                    }
                } else {//재생중 아님
                    MainActivity.pands.setBackgroundResource(R.drawable.bottom_pause);
                    List<String> pp = new ArrayList<>();
                    for (int i = 0; i < MainActivity.playingList.size(); i++) {
                        pp.add(MainActivity.playingList.get(i).getPnp());
                        if (i == MainActivity.playingList.size() - 1) {
                            //playinglist start
                            AudioController.startPlayingList(context, pp);
                        }
                    }
                }
                break;
            case DefaultNofitication.ACTION_CLOSE:
                if (MainActivity.playingList.size() != 0) {
                    MainActivity.pands.setBackgroundResource(R.drawable.bottom_play);
                    if (NotificationService.isPlaying) {
                        context.stopService(new Intent(context, NotificationService.class));
                    }
                    stopAllSound();
                }
                break;
        }
    }

    private void stopAllSound() {
        for (int i = 0; i < MainActivity.playingList.size(); i++) {
            stopPage(MainActivity.playingList.get(i).getPage());
        }
    }

    private void stopPage(int page) {
        if (page == 1) {
            RainController.stopPage1();
        } else if (page == 2) {
            WindController.stopPage2();
        }
    }

    private void checkOpenService(Context context) {
        if (!NotificationService.isPlaying) {
            Intent intent = new Intent(context, NotificationService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }
}
