package com.example.demo_service.Demo05_Foreground_Media_Control;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.demo_service.R;


public class MyService extends Service {

    // Khai báo trình phát nhạc trong Android
    private MediaPlayer mediaPlayer;

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
        // get bundle từ "MainActivity.java" gửi qua thông qua hàm startService()
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Song song = (Song) bundle.get("object_song");
            if(song != null){
                startMusic(song);

                sendNotification(song);
            }
        }

        return START_NOT_STICKY; // không cần chạy lại khi bi kill
                                 // không cần chạy lại khi hệ thống bắt buộc dừng
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // khi Service này bị destroy thì ta giải phóng
        // trình media luôn
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    private void startMusic(Song song) {
        if(mediaPlayer == null) {
            // Khởi tạo
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }

        mediaPlayer.start();
    }

    private void sendNotification(Song song) {
        Intent intent = new Intent(this, Main_Demo05.class);
        // Định nghĩa hành động khi click lên Notification,
        // là mở activity "Main_Demo_Foreground_Control.java"
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), song.getImage());

        // RemoteViews: dùng để inflate layout custom
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.tv_title_song, song.getTitle());
        remoteViews.setTextViewText(R.id.tv_singer_song, song.getSinger());
        remoteViews.setImageViewBitmap(R.id.img_song, bitmap);
        remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.ic_pause);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                // Vì ta đã set layout ở RemoteViews rồi nên
                // ta không cần sử dụng các method: .setContentTitle() / .setContentText()
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent) // tạo hành động khi click lên Notification:
                                                 // Khi click lên, sẽ chuyển tới activity
                                                 // Main_Demo_ForegroundService bằng intent
                // set layout custom_notification vào Notification
                .setCustomContentView(remoteViews)
                .setSound(null) // Tắt âm thanh Notification cho Android 8-
                                // Tắt âm thanh Notification cho Android 8+
                                // ở Channel ID trong file MyApplication.java
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
