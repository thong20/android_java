package com.example.projectandroid.Demo09;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projectandroid.R;

import java.util.Date;

public class Main_Demo09 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo09);

        Button btn_send_notification = findViewById(R.id.btn_send_notification);
        Button btn_go_to_list_product = findViewById(R.id.btn_go_to_list_product);

        btn_send_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPushNotification();
            }
        });

        btn_go_to_list_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void sendPushNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.headphones_red);


        // COPY FROM HOMEPAGE GUIDES
        // LINK: https://developer.android.com/training/notify-user/navigation#ExtendedNotification
        Intent notifyIntent = new Intent(this, DetailActivity.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_1)
                .setContentTitle("thong20 - My title")
                .setContentText("thong20 - My content here")
                // set pendingIntent
                .setContentIntent(notifyPendingIntent)
                // Xóa notification sau khi touch
                .setAutoCancel(true)
                // set priority for Android 8--
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.favorite_green)
                .setLargeIcon(bitmap)
                .setColor(Color.YELLOW)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(random(), notification);

    }

    private int random(){
        return (int) new Date().getTime();
    }

}