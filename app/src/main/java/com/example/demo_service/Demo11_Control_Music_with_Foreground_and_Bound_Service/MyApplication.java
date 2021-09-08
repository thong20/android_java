package com.example.demo_service.Demo11_Control_Music_with_Foreground_and_Bound_Service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "CHANNEL_EXAMPLE";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel example",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if(manager != null){
                manager.createNotificationChannel(channel);
            }
        }

    }
}
