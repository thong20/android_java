package com.example.projectandroid.Demo06;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
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
            Uri uri_1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Uri uri_2 = Uri.parse("android.resource://" +getPackageName()+ "/" + R.raw.my_sound);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            // CONFIG CHANNEL 1
            CharSequence name = getString(R.string.channel1_name);
            String description = getString(R.string.channel1_description);
            // set priority for Android 8+
            int importance = NotificationManager.IMPORTANCE_MIN;
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_1,
                    name,
                    importance);
            channel.setDescription(description);
            // set default sound for Android 8+
            channel.setSound(uri_1, audioAttributes);

            // CONFIG CHANNEL 2
            CharSequence name_2 = getString(R.string.channel2_name);
            String description_2 = getString(R.string.channel2_description);
            // set priority for Android 8+
            int importance_2 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel_2 = new NotificationChannel(
                    CHANNEL_ID_2,
                    name_2,
                    importance_2);
            channel_2.setDescription(description_2);
            // set custom sound for Android 8+
            channel_2.setSound(uri_2, audioAttributes);

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
