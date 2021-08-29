package com.example.projectandroid.Demo08;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class MyApplication extends Application {

    public static final String CHANNEL_ID_1 = "CHANNEL_1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            // CONFIG FOR CHANNEL
            CharSequence name = "Channel 1";
            String description = "This is channel 1";
            // set priority for Android 8+
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_1,
                    name,
                    importance
            );
            channel.setDescription(description);
            // set sound for Android 8+
            channel.setSound(uri, audioAttributes);

            // REGISTER THE CHANNEL WITH THE SYSTEM
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }

        }
    }
}
