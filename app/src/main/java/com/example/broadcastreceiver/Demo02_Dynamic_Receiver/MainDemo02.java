package com.example.broadcastreceiver.Demo02_Dynamic_Receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.broadcastreceiver.R;

public class MainDemo02 extends AppCompatActivity {

    ExampleBroadcastReceiver exampleBroadcastReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        // KHAI BÁO BROADCAST
        // Khai báo IntentFilter cho activiy MainActivity
        // để hệ thống biết là ứng dụng có thể
        // nhận được các dạng dữ liệu
        // Trong demo này ta khai báo cho IntentFilter là
        // action với tên "android.net.conn.CONNECTIVITY_CHANGE"
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo02);

        exampleBroadcastReceiver = new ExampleBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // HỦY ĐĂNG KÝ KHI ACTIVITY onStop()
        unregisterReceiver(exampleBroadcastReceiver);

    }




    // ====================================================================
    public void Logd(String str){
        Log.d("Log.d", "=== MainActivity.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== MainActivity.java - line: " + n + " ==============================\n" + str);
    }
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== MainActivity.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== MainActivity.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
    public void showToast(Context context, String str ){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}