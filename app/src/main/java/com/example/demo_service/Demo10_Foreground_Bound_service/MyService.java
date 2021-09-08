package com.example.demo_service.Demo10_Foreground_Bound_service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.demo_service.R;

import static com.example.demo_service.Demo10_Foreground_Bound_service.MyApplication.CHANNEL_ID;

public class MyService extends Service {

    private MyBinder myBinder = new MyBinder();

    class MyBinder extends Binder{
        // method
        MyService getMyService(){
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        Logdln("onCreate()", 15);
        super.onCreate();
    }

    // Hàm ràng buộc cho Bound Service
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logdln("onBind()", 23);
        return myBinder; // vì trong Demo này ta có sử dụng Bound Service nên
                         // ta phải return 1 IBinder
    }

    // Hàm khởi chạy Foreground Service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logdln("onStartCommand()", 30);

        sendNotification();
        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logdln("ouUnbind()", 36);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Logdln("onDestroy()", 42);
        super.onDestroy();
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("thong20 - Title here");
        builder.setContentText("thong20 - Content text here");
        builder.setSmallIcon(R.drawable.ic_small_music);

        Notification notification = builder.build();

        startForeground(1, notification);
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
