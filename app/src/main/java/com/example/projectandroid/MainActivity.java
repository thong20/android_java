package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.Demo01_bindService.Main_Demo_bindService_01;
import com.example.projectandroid.Demo02_startService.Main_Demo_startService_01;
import com.example.projectandroid.Demo03_Foreground_Notification.Main_Demo_Foreground_Notification;
import com.example.projectandroid.Demo04_Foreground_Media_Control.Main_Demo_Foreground_Control;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_01, btn_02,
            btn_03, btn_04;

// test remote
    // thong20


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        btn_03 = findViewById(R.id.btn_03);
        btn_04 = findViewById(R.id.btn_04);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
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
        if(v.getId() == R.id.btn_04){
            startActivity(new Intent(this, Main_Demo_Foreground_Control.class));
        }



    }
}