package com.example.demo_service.Demo06_Foreground_BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // get data từ Notification qua intent của method ".getPendingIntent()" trong class "MyService.java"
        //
        int actionMusic = intent.getIntExtra("action_music", 0);

        // Truyền lại cho class Service
        Intent intentService = new Intent(context, MyService.class);
        intentService.putExtra("action_music_service", actionMusic);
        context.startService(intentService);
    }
}
