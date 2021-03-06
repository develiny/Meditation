package com.develiny.meditation.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;

public class DefaultNofitication {

    public static final String CHANNEL_ID = "channel1";

    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_CLOSE = "actionclose";

    public static void defauleNotification (Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
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

            if (AudioController.checkIsPlaying(MainActivity.playingList.get(0).getPnp())) {
                notification.addAction(R.drawable.bottom_pause, "Play", pendingIntentPlay);
            } else {
                notification.addAction(R.drawable.bottom_play, "Play", pendingIntentPlay);
            }
//            notification.addAction(R.drawable.bottom_play, "Play", pendingIntentPlay);
            notification.addAction(R.drawable.notification_close, "close", pendingIntentClose);

            notification.setContentIntent(pIntent);
            notification.setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1).
                    setMediaSession(mediaSessionCompat.getSessionToken()));
            notification.setPriority(NotificationCompat.PRIORITY_LOW);//PRIORITY_LOW

            notificationManagerCompat.notify(1, notification.build());
            Log.d(">>>", "open foreground");
        }
    }

    public static void closeNotification(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.cancelAll();
    }

}
