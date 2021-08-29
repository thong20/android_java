package com.example.projectandroid.Demo05;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projectandroid.R;

import java.util.Date;

public class Main_Demo05 extends AppCompatActivity {
    private static final String TITLE_PUSH_NOTIFICATION = "11 dấu hiệu bất thường F0 liên hệ y tế ngay";
    private static final String CONTENT_PUSH_NOTIFICATION = "F0 cách ly tại nhà thấy khó thở, nhịp thở nhanh, SpO2 ≤ 95%, huyết áp thấp, đau tức ngực, thay đổi ý thức... cần liên hệ y tế ngay.";
    Button btn_send_notification_1,
            btn_send_notification_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo05);

        btn_send_notification_1 = findViewById(R.id.btn_send_notification_1);
        btn_send_notification_2 = findViewById(R.id.btn_send_notification_2);

        btn_send_notification_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification_1();
            }
        });
        btn_send_notification_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification_2();
            }
        });

    }

    private void sendNotification_1() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.headphones_red);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // (1) - Khởi tạo Notification
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_1)
                .setContentTitle(TITLE_PUSH_NOTIFICATION)
                .setContentText(CONTENT_PUSH_NOTIFICATION)
                // set default sound for Android 8-
                .setSound(uri)
                // set big text
                .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTIFICATION))
                .setSmallIcon(R.drawable.favorite_green)
                .setLargeIcon(bitmap) // Sử dụng BitmapFactory
                .setColor(Color.RED)
                .build();

        // (2) - Cách 2 - Sử dụng NotificationManagerCompat
        // Khi sử dụng cách này thì ta không cần phải check "null" như cách 1
        // vì trong class NotificationManagerCompat đã xử lý check null rồi
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(random(), notification);
    }

    private void sendNotification_2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xgame);
        Uri uri = Uri.parse("android.resource://" +getPackageName()+ "/" + R.raw.my_sound);

        // (1) - Khởi tạo Notification
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("thong20 - Title push notification channel 2")
                .setContentText("thong20 - Message push notification channel 2")
                // set custom sound from Android 8-
                .setSound(uri)
                // set big picture
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setSmallIcon(R.drawable.ic_favorite)
                .setLargeIcon(bitmap) // Sử dụng BitmapFactory
                .setColor(Color.GREEN)
                .build();

        // (2) - Cách 2 - Sử dụng NotificationManagerCompat
        // Khi sử dụng cách này thì ta không cần phải check "null" như cách 1
        // vì trong class NotificationManagerCompat đã xử lý check null rồi
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(random(), notification);
    }

    private int random(){
        return (int) new Date().getTime();
    }
}