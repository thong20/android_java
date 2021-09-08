package com.example.demo_service.Demo11_Control_Music_with_Foreground_and_Bound_Service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.demo_service.R;

import static com.example.demo_service.Demo11_Control_Music_with_Foreground_and_Bound_Service.MyApplication.CHANNEL_ID;

public class MyService extends Service {

    // Tạo biến mSong để "Main_Demo11.java" truy xuất
    // Để truy xuất ta phải tạo hàm GETTER cho biến này
    // trong chính class này là "MyService"
    Song mSong;

    private MediaPlayer mMediaPlayer;
    private boolean isPlaying;

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

        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Song song = (Song) bundle.get("object_song");
            mSong = song;

            startMusic(song);
            sendNotification(song);
        }

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
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        super.onDestroy();
    }

    // START - HÀM GETTER ========================================
    public Song getSong() {
        return mSong;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
    // END - HÀM GETTER ===========================================

    private void startMusic(Song song) {
        if(mMediaPlayer == null){
            // Khởi tạo trình phát nhạc MediaPlayer
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }
        mMediaPlayer.start();
        isPlaying = true;
    }

    public void pauseMusic(){
        if(mMediaPlayer != null && isPlaying){
            mMediaPlayer.pause();
            isPlaying = false;
        }
    }
    public void resumeMusic(){
        if(mMediaPlayer != null && !isPlaying){
            mMediaPlayer.start();
            isPlaying = true;
        }
    }

    private void sendNotification(Song song) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("thong20 - Title here");
        builder.setContentText("thong20 - " + song.getName());
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
