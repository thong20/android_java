package com.example.projectandroid.Demo07;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.projectandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main_Demo07 extends AppCompatActivity {
    private static final String TITLE_PUSH_NOTIFICATION = "11 dấu hiệu bất thường F0 liên hệ y tế ngay";
    private static final String CONTENT_PUSH_NOTIFICATION = "F0 cách ly tại nhà thấy khó thở, nhịp thở nhanh, SpO2 ≤ 95%, huyết áp thấp, đau tức ngực, thay đổi ý thức... cần liên hệ y tế ngay.";
    Button btn_send_notification_1,
            btn_send_notification_2,
            btn_send_notification_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo07);

        btn_send_notification_1 = findViewById(R.id.btn_send_notification_1);
        btn_send_notification_2 = findViewById(R.id.btn_send_notification_2);
        btn_send_notification_3 = findViewById(R.id.btn_custom_notification);

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
        btn_send_notification_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification_3();
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
                // set priority ở cấp "Low"
                // cấp này sẽ không có sound dù có setSound()
                .setPriority(NotificationCompat.PRIORITY_MIN)
                // set default sound for Andorid 8-
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
                // set priority for Android 8-
                .setPriority(NotificationCompat.PRIORITY_MAX)
                // set custom sound for Android 8-
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

    private void sendNotification_3() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String myTime = df.format(new Date().getTime());

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xgame);
        Uri uri = Uri.parse("android.resource://" +getPackageName()+ "/" + R.raw.my_sound);

        // COLLAPSED NOTIFICATION =====================================
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        // set nội dung cho TextView title
        notificationLayout.setTextViewText(
                R.id.tv_title_custom_notification,
                "thong20 - Title custom"
                );
        // set nội dung cho TextView message
        notificationLayout.setTextViewText(
                R.id.tv_message_custom_notification,
                "thong20 - Message custom notification"
                );
        // set nội dung cho TextView time
        notificationLayout.setTextViewText(
                R.id.tv_time_custom_notification,
                myTime
        );

        // EXPAND NOTIFICATION =====================================
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.layout_custom_notification_expand);
        // set nội dung cho TextView title
        notificationLayoutExpanded.setTextViewText(
                R.id.tv_title_custom_notification_expanded,
                "thong20 - Title custom"
        );
        // set nội dung cho TextView message
        notificationLayoutExpanded.setTextViewText(
                R.id.tv_message_custom_notification_expanded,
                "thong20 - Message custom notification"
        );
        // set nội dung cho TextView time
        notificationLayoutExpanded.setImageViewResource(
                R.id.img_custom_notification_expanded,
                R.drawable.xgame
        );


        // NOTIFICATION =====================================
        // (1) - Khởi tạo Notification
        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                // set layout expand
                .setCustomBigContentView(notificationLayoutExpanded)
                // set layout collapsed
                .setCustomContentView(notificationLayout)
                // set custom sound for Android 8-
                .setSound(uri)
                // set big picture
                .setSmallIcon(R.drawable.ic_favorite)
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