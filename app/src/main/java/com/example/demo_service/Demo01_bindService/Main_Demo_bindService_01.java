package com.example.demo_service.Demo01_bindService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_service.R;

public class Main_Demo_bindService_01 extends AppCompatActivity {
    Button button, button_unbind, button_rebind;
    LocalService mService;
    boolean mBound = false;

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo_bind_service);
        button = findViewById(R.id.button);
        button_unbind = findViewById(R.id.btn_unbind);
        button_rebind = findViewById(R.id.btn_rebind);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    // Call a method from the LocalService.
                    // However, if this call were something that might hang, then this request should
                    // occur in a separate thread to avoid slowing down the activity performance.
                    int num = mService.getRandomNumber();
                    showToast("number: " + num);
                }
            }
        });

        button_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        button_rebind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bind to LocalService
                Intent intent = new Intent(Main_Demo_bindService_01.this, LocalService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    // =========================================================
    public void Logd(String str){
        Log.d("Log.d", "=== Main_Demo_bindService_01.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== Main_Demo_bindService_01.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}