package com.example.demo_service.Demo01_bindService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logdln("onCreate()", 31);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logdln("onBind()", 36);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logdln("onUnbound()", 41);
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Logdln("onRebind()", 48);
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logdln("onDestroy()", 54);
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    // =========================================================
    public void Logd(String str){
        Log.d("Log.d", "=== LocalService.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== LocalService.java - line: " + n + " ==============================\n" + str);
    }
}