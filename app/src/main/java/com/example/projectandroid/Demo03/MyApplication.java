package com.example.projectandroid.Demo03;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.projectandroid.R;

public class MyApplication extends Application {

    public static final String CHANNEL_ID_1 = "CHANNEL_1";
    public static final String CHANNEL_ID_2 = "CHANNEL_2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // CONFIG CHANNEL 1
            CharSequence name = getString(R.string.channel1_name);
            String description = getString(R.string.channel1_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_1,
                    name,
                    importance);
            channel.setDescription(description);

            // CONFIG CHANNEL 2
            CharSequence name_2 = getString(R.string.channel2_name);
            String description_2 = getString(R.string.channel2_description);
            int importance_2 = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel_2 = new NotificationChannel(
                    CHANNEL_ID_2,
                    name_2,
                    importance_2);
            channel_2.setDescription(description_2);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel_2);
            }
        }
    }
}
