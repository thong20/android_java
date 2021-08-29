package com.example.projectandroid.Demo03;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projectandroid.R;

import java.util.Date;

public class Main_Demo03 extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;

    Button btn_send_notification_1,
            btn_send_notification_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo03);

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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_headphones);
        // (1) - Khởi tạo Notification
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_1)
                .setContentTitle("thong20 - Title push notification")
                .setContentText("thong20 - Message push notification")
                .setSmallIcon(R.drawable.ic_favorite)
                .setLargeIcon(bitmap) // Sử dụng BitmapFactory
                .setColor(Color.RED)
                .build();

        // (2) - Cách 1 - Khởi tạo NotificationManager
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(notificationManager != null){
//            // Tham số thứ 1:
//            // Khi gọi lại method .notify(), hệ thống sẽ lấy tham số thứ nhất
//            // so sánh với lần gọi trước đó có bằng nhau không?
//            // - Nếu bằng nhau thì nó sẽ ghi đè lên notification trước đó
//            // dẫn đến => nó chỉ xuất hiện 1 notification
//            // - Nếu không bằng nhau, thì hệ thống sẽ xuất tiếp 1 notification
//            // mới => dẫn đến xuất hiện nhiều notification
////            notificationManager.notify(NOTIFICATION_ID, notification);
//            notificationManager.notify(random(), notification);
//
//        }

        // (2) - Cách 2 - Sử dụng NotificationManagerCompat
        // Khi sử dụng cách này thì ta không cần phải check "null" như cách 1
        // vì trong class NotificationManagerCompat đã xử lý check null rồi
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(random(), notification);
    }

    private void sendNotification_2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_headphones);
        // (1) - Khởi tạo Notification
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("thong20 - Title push notification channel 2")
                .setContentText("thong20 - Message push notification channel 2")
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