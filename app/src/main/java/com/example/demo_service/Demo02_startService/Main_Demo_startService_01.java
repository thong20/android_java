package com.example.demo_service.Demo02_startService;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_service.R;

public class Main_Demo_startService_01 extends AppCompatActivity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo_start_service01);

        button = findViewById(R.id.btn);

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("test", "abc123");

        // khi gọi hàm này, thì hàm onStartCommand()
        // sẽ được thực thi
        startService(intent);
        // khi gọi hàm này, thì hàm onStartCommand()
        // sẽ ngừng thực thi
//        stopService(...);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== Demo_startService_01.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== Demo_startService_01.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== Demo_startService_01.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== Demo_startService_01.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}