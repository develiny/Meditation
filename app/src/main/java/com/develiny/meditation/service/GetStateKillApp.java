package com.develiny.meditation.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.develiny.meditation.databasehandler.DatabaseHandler;

public class GetStateKillApp extends Service {
    DatabaseHandler databaseHandler;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        //my code
        databaseHandler = new DatabaseHandler(getApplicationContext());
        databaseHandler.whenAppKillTask();
        stopSelf();
    }
}
