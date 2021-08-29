package com.example.projectandroid.Demo10;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANNEL_ID_1 = "CHANNEL_MUSIC_APP";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            // CONFIG FOR CHANNEL
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            // set priority for Androi 8++
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_1,
                    name,
                    importance
            );
            channel.setDescription(description);

            // REGISTER THE CHANNEL WITH THE SYSTEM
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
