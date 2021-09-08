package com.example.demo_service.Demo09_BoundService_Messenger;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo_service.R;

public class MusicService extends Service {

    private MediaPlayer mMediaPlayer;

// START - GUIDES FROM HOMEPAGE: https://developer.android.com/guide/components/bound-services#Messenger =============
    public static final int MSG_PLAY_MUSIC = 1;
    private Messenger mMessenger;
    public class MyHandler extends Handler{
        private Context applicationContext;

        public MyHandler(Context context) {
            this.applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MSG_PLAY_MUSIC:
                    startMusic();
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }


        }
    }
// END - GUIDE FROM HOMEPAGE =====================================================================

    @Override
    public void onCreate() {
        super.onCreate();
        Logdln("onCreate()", 52);
    }

    // Hàm ràng buộc cho Bound Service
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logdln("onBind()", 58);
        // Khởi tạo Messenger
        mMessenger = new Messenger(new MyHandler(this));
        return mMessenger.getBinder(); // vì trong Demo này ta sử dụng Bound Service nên
                                       // ta phải return 1 IBinder
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logdln("onUnbind()", 67);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logdln("onDestroy()", 74);
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void startMusic(){
        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(this, R.raw.somewhere_somehow);
        }
        mMediaPlayer.start();
    }

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== MusicService.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== MusicService.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== MusicService.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== MusicService.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
