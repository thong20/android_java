package com.example.projectandroid.Demo08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectandroid.R;

import java.util.Date;

public class Main_Demo08 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo08);

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
        // LINK: https://developer.android.com/training/notify-user/navigation#build_a_pendingintent_with_a_back_stack
        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, DetailActivity.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(random(), PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_1)
                .setContentTitle("thong20 - My title")
                .setContentText("thong20 - My content here")
                // set pendingIntent
                .setContentIntent(resultPendingIntent)
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

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== Main_Demo08.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== Main_Demo08.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== Main_Demo08.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== Main_Demo08.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}