package com.example.demo_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_service.Demo01_bindService.Main_Demo_bindService_01;
import com.example.demo_service.Demo02_startService.Main_Demo_startService_01;
import com.example.demo_service.Demo03_Foreground_Notification.Main_Demo_Foreground_Notification;
import com.example.demo_service.Demo05_Foreground_Media_Control.Main_Demo05;
import com.example.demo_service.Demo06_Foreground_BroadcastReceiver.Main_Demo06;
import com.example.demo_service.Demo07_Foreground_Notification_MediaStyle.Main_Demo07;
import com.example.demo_service.Demo08_BoundService_Binder_class.Main_Demo08;
import com.example.demo_service.Demo09_BoundService_Messenger.Main_Demo09;
import com.example.demo_service.Demo10_Foreground_Bound_service.Main_Demo10;
import com.example.demo_service.Demo11_Control_Music_with_Foreground_and_Bound_Service.Main_Demo11;
import com.example.demo_service.Demo12_Background_Service_basic.Main_Demo12;
import com.example.demo_service.Demo13_Background_Service_with_JobScheduler.Main_Demo13;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_01, btn_02, btn_03, btn_05,
            btn_06, btn_07, btn_08, btn_09, btn_10,
            btn_11, btn_12, btn_13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        btn_03 = findViewById(R.id.btn_03);
        btn_05 = findViewById(R.id.btn_05);
        btn_06 = findViewById(R.id.btn_06);
        btn_07 = findViewById(R.id.btn_07);
        btn_08 = findViewById(R.id.btn_08);
        btn_09 = findViewById(R.id.btn_09);
        btn_10 = findViewById(R.id.btn_10);
        btn_11 = findViewById(R.id.btn_11);
        btn_12 = findViewById(R.id.btn_12);
        btn_13 = findViewById(R.id.btn_13);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_05.setOnClickListener(this);
        btn_06.setOnClickListener(this);
        btn_07.setOnClickListener(this);
        btn_08.setOnClickListener(this);
        btn_09.setOnClickListener(this);
        btn_10.setOnClickListener(this);
        btn_12.setOnClickListener(this);
        btn_13.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_01){
            startActivity(new Intent(this, Main_Demo_startService_01.class));
        }
        if(v.getId() == R.id.btn_02){
            startActivity(new Intent(this, Main_Demo_bindService_01.class));
        }
        if(v.getId() == R.id.btn_03){
            startActivity(new Intent(this, Main_Demo_Foreground_Notification.class));
        }
        if(v.getId() == R.id.btn_05){
            startActivity(new Intent(this, Main_Demo05.class));
        }
        if(v.getId() == R.id.btn_06){
            startActivity(new Intent(this, Main_Demo06.class));
        }
        if(v.getId() == R.id.btn_07){
            startActivity(new Intent(this, Main_Demo07.class));
        }
        if(v.getId() == R.id.btn_08){
            startActivity(new Intent(this, Main_Demo08.class));
        }
        if(v.getId() == R.id.btn_09){
            startActivity(new Intent(this, Main_Demo09.class));
        }
        if(v.getId() == R.id.btn_10){
            startActivity(new Intent(this, Main_Demo10.class));
        }
        if(v.getId() == R.id.btn_11){
            startActivity(new Intent(this, Main_Demo11.class));
        }
        if(v.getId() == R.id.btn_12){
            startActivity(new Intent(this, Main_Demo12.class));
        }
        if(v.getId() == R.id.btn_13){
            startActivity(new Intent(this, Main_Demo13.class));
        }



    }
}