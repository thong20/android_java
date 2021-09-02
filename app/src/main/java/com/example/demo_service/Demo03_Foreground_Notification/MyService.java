package com.example.demo_service.Demo03_Foreground_Notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.demo_service.R;


public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Logdln("onCreate()", 17);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null; // vì Demo này sử dụng Foreground nên ta return null
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String strDataIntent = intent.getStringExtra("key_data_intent");

        sendNotification(strDataIntent);

        return START_NOT_STICKY; // không cần chạy lại khi bi kill
                                 // không cần chạy lại khi hệ thống bắt buộc dừng
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    private void sendNotification(String strDataIntent) {
        Intent intent = new Intent(this, Main_Demo_Foreground_Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("This is Title / MyService.java - line: 64")
                .setContentText(strDataIntent)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent) // tạo hành động khi click lên Notification:
                                                 // Khi click lên, sẽ chuyển tới activity
                                                 // Main_Demo_ForegroundService bằng intent
                .build();

        // KHỞI CHẠY NOTIFICATION Ở DẠNG FOREGROUND SERVICE
        // ............param1 là tự đặt
        startForeground(1, notification);
        // Lưu ý, nếu không khởi chạy thì ứng dụng sẽ bị
        // kill sau khoảng thời gian hơn 1 phút
    }

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== MyService.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== MyService.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== MyService.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== MyService.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
