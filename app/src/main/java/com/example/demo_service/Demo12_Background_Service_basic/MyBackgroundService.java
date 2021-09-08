package com.example.demo_service.Demo12_Background_Service_basic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.demo_service.R;

public class MyBackgroundService extends Service {

    MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        Logdln("onCreate()", 22);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logdln("onBind()", 28);

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logdln("onStartCommand()", 35);
        // TODO
        startMusic();

        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logdln("onUnbind()", 44);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logdln("onDestroy()", 45);

        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void startMusic() {
        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.somewhere_somehow);
        }
        mMediaPlayer.start();

    }

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== MyBackgroundService.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== MyBackgroundService.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== MyBackgroundService.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== MyBackgroundService.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
