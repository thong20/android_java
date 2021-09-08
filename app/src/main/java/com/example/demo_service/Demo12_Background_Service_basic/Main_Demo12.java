package com.example.demo_service.Demo12_Background_Service_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo_service.R;

public class Main_Demo12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo12);

        Button btnStartBackgroundService = findViewById(R.id.btn_start_service);

        btnStartBackgroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });
    }

    private void onClickStartService() {
        Intent intent = new Intent(this, MyBackgroundService.class);
        startService(intent);
    }
}