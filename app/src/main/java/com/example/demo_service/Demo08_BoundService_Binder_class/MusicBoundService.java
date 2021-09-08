package com.example.demo_service.Demo08_BoundService_Binder_class;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.demo_service.R;

public class MusicBoundService extends Service {

    private MyBinder mBinder = new MyBinder();
    private MediaPlayer mediaPlayer;

    class MyBinder extends Binder {
        MusicBoundService getMusicBoundService(){
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Logdln("onCreate()", 26);
    }

    // Hàm ràng buộc cho Bound Service
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logdln("onBind()", 32);
        return mBinder; // vì trong Demo này ta sử dụng Bound Service nên
                        // ta phải return 1 IBinder
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logdln("onUnbind()", 39);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logdln("onDestroy();", 46);
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void startMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.somewhere_somehow);
        }
        mediaPlayer.start();
    }


    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== MusicBoundService.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== MusicBoundService.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== MusicBoundService.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== MusicBoundService.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
