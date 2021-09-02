package com.example.demo_service.Demo02_startService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logdln("onBind()", 15);
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Logdln("onStartCommand()", 22);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logdln("onCreate()", 29);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logdln("onUnbind() - return true", 34);
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Logdln("onRebind()", 41);
    }

    @Override
    public void onDestroy() {
        Logdln("onDestroy()", 46);
        super.onDestroy();
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
}


























