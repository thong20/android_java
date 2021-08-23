package com.example.projectandroid.Demo04_Foreground_Media_Control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // get data từ intent của method ".getPendingIntent()" của class "MyService.java"
        int actionMusic = intent.getIntExtra("action_music", 0);

        Intent intentService = new Intent(context, MyReceiver.class);
        intentService.putExtra("action_music_service", actionMusic);

        context.startService(intentService);
    }
}
