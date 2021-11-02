package com.develiny.meditation.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.controller.P1Controller;
import com.develiny.meditation.controller.P2Controller;

import java.util.ArrayList;
import java.util.List;

public class NotificationService extends Service {

    public static final String CHANNEL_ID = "channel1";

    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_CLOSE = "actionclose";

    public static boolean isPlaying;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isPlaying = true;
        startForgroundService(getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isPlaying = false;
        Log.d("NotificationService>>>", "onDestroy");
        //여기서 재생중인곡 있으면 종료하기
        if (MainActivity.playingList.size() != 0 && AudioController.checkIsPlaying(MainActivity.playingList.get(0).getPnp())) {//재생중
            MainActivity.pands.setBackgroundResource(R.drawable.bottom_play);
            List<Integer> page = new ArrayList<>();
            for (int i = 0; i < MainActivity.playingList.size(); i++) {
                page.add(MainActivity.playingList.get(i).getPage());
                if (i == MainActivity.playingList.size() - 1) {
                    NotificationService.stopPlayingList(page);
                }
            }
        }
        super.onDestroy();
    }

    public void startForgroundService(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_head);

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            Intent intentPlay = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_PLAY);
            Intent intentClose = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_CLOSE);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntentClose = PendingIntent.getBroadcast(context, 0, intentClose,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
                    0);

            NotificationCompat.Builder notification;
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "RRRain", NotificationManager.IMPORTANCE_DEFAULT);
                ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
                notification = new NotificationCompat.Builder(context, CHANNEL_ID);
            } else {
                notification = new NotificationCompat.Builder(context);
            }
            notification.setSilent(true);
            notification.setSmallIcon(R.drawable.bottom_play);
            notification.setContentTitle("meditation title");//.setContentText(track.getName())
            notification.setLargeIcon(icon);
            notification.setOnlyAlertOnce(true);//show notification for only first time
            notification.setShowWhen(false);

            notification.addAction(R.drawable.bottom_pause, "Play", pendingIntentPlay);
            notification.addAction(R.drawable.notification_close, "close", pendingIntentClose);

            notification.setContentIntent(pIntent);
            notification.setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1).
                    setMediaSession(mediaSessionCompat.getSessionToken()));
            notification.setPriority(NotificationCompat.PRIORITY_LOW);//PRIORITY_LOW
//                    .build();

//            notificationManagerCompat.notify(1, notification);
            startForeground(1, notification.build());
//            stopForeground(true);
//            stopSelf();
//            Log.d(">>>", "open foreground");
        }
    }

    public static void stopPlayingList(List<Integer> page) {//playinglist에 있는 목록만 stop(page)
        for (int i = 0; i < page.size(); i++) {
            if (page.get(i) == 1) {
                P1Controller.stopPage1();
            } else if (page.get(i) == 2) {
                P2Controller.stopPage2();
            }
        }
    }
}
