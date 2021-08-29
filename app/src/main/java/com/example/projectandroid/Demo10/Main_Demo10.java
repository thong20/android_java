package com.example.projectandroid.Demo10;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projectandroid.R;

import java.util.Date;

public class Main_Demo10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo10);

        Button btn_send_notification = findViewById(R.id.btn_send_notification);
        btn_send_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificationMedia();
            }
        });

    }

    private void sendNotificationMedia() {
        // Convert jpg/png to bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xgame);

        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, "tag");

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_1)
                .setContentTitle("thong20 - ContentTitle")
                .setContentText("thong20 - ContentText")
                .setSmallIcon(R.drawable.ic_small_music)
                .setSubText("thong20 - SubText")
                .setLargeIcon(bitmap)

                // COPY FROM HOMEPAGE GUIDES:
                // https://developer.android.com/training/notify-user/expanded#media-style
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_previous, "Previous", null) // #0
                .addAction(R.drawable.ic_pause, "Pause", null)       // #1
                .addAction(R.drawable.ic_next, "Next", null)         // #2
                // Apply the media style template
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2 /* #1: pause button */)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))

                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(random(), notification);

    }

    private int random() {
        return (int) new Date().getTime();
    }
}
