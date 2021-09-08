package com.example.demo_service.Demo10_Foreground_Bound_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.demo_service.R;

public class Main_Demo10 extends AppCompatActivity {

    private MyService myService;
    private boolean isServiceConnected;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            myService = myBinder.getMyService();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo10);

        Button btnStartService          = findViewById(R.id.btn_start_service);
        Button btnStopForegroundService = findViewById(R.id.btn_stop_foreground_service);
        Button btnStopBoundService      = findViewById(R.id.btn_stop_bound_service);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });

        btnStopForegroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopForegroundService();
            }
        });

        btnStopBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopBoundService();
            }
        });

    }


    private void onClickStartService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        // Ràng buộc cho Bound Service
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onClickStopForegroundService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void onClickStopBoundService() {
        if(isServiceConnected){
            unbindService(mServiceConnection);
            isServiceConnected = false;
        }
    }

}