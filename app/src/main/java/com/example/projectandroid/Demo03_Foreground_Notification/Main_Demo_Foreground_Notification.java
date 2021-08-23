package com.example.projectandroid.Demo03_Foreground_Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectandroid.R;

public class Main_Demo_Foreground_Notification extends AppCompatActivity {
    EditText edtDataIntent;
    Button btnStartService, btnStopService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo_foreground_notification);

        edtDataIntent = findViewById(R.id.et_data_intent);
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickStartService();
            }
        });
        
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickStopService();
            }
        });
        
    }


    private void clickStartService() {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("key_data_intent", edtDataIntent.getText().toString().trim());

        // chạy ForegroundService
        startService(intent);
    }

    private void clickStopService() {
        Intent intent = new Intent(this, MyService.class);

        // stop
        stopService(intent);
    }
}
