package com.example.projectandroid.Demo04_Foreground_Media_Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectandroid.R;

/**
 * Link: https://youtu.be/uZsCuGte_eY
 */

public class Main_Demo_Foreground_Control extends AppCompatActivity {
    EditText edtDataIntent;
    Button btnStartService, btnStopService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo_foreground_control);

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
        Song song = new Song("Big city boy", "Tincoder", R.drawable.image_song, R.raw.somewhere_somehow);

        Intent intent = new Intent(this, MyService.class);
        // Sử dụng Bundle để gửi lên Push Notification
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", song);
        intent.putExtras(bundle);

        // chạy ForegroundService
        startService(intent);
    }

    private void clickStopService() {
        Intent intent = new Intent(this, MyService.class);

        // stop
        stopService(intent);
    }
}
