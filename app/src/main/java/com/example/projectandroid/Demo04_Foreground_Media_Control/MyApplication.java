package com.example.projectandroid.Demo04_Foreground_Media_Control;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "channel_service_example";

    @Override
    public void onCreate() {
        super.onCreate();

        createChannelNotification();

    }

    private void createChannelNotification() {
        // Check version Android
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel Service Example",
                    NotificationManager.IMPORTANCE_DEFAULT // Độ ưu tiên: số càng lớn độ ưu tiên càng cao
            );
            // Tăng âm thanh thông báo của Notification cho Android 8+
            channel.setSound(null, null);


            // Initial manager
            NotificationManager manager = getSystemService(NotificationManager.class);
            // vì "manager" có thể null nên ta sẽ check null
            if(manager != null){
                // tạo channel
                manager.createNotificationChannel(channel);

            }
        }
    }
}
