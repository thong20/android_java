package com.example.demo_service.Demo06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.demo_service.R;

import static com.example.demo_service.Demo06.MyService.SEND_DATA_TO_ACTIVITY;

/**
 * Link: https://youtu.be/uZsCuGte_eY
 */

public class Main_Demo06 extends AppCompatActivity {
    EditText edtDataIntent;
    Button btnStartService, btnStopService;
    RelativeLayout layout_bottom;
    ImageView imgSong, imgPlayOrPause, imgClear;
    TextView tvTitleSong, tvSingerSong;

    Song mSong;
    boolean isPlaying;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle == null){
                return;
            }
            mSong = (Song) bundle.get("object_song");
            isPlaying = bundle.getBoolean("status_player");
            int actionMusic = bundle.getInt("action_music");

            handleLayoutMusic(actionMusic);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo06);

        // Register lắng nghe Broadcast
        LocalBroadcastManager.getInstance(this).registerReceiver(
                broadcastReceiver,
                new IntentFilter(SEND_DATA_TO_ACTIVITY) // Tên action của Intent bên MyService.java
                                                        // trong hàm sendActionToActivity()
        );

        edtDataIntent = findViewById(R.id.et_data_intent);
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        layout_bottom = findViewById(R.id.layout_bottom);
        imgSong = findViewById(R.id.img_song);
        imgPlayOrPause = findViewById(R.id.img_play_or_pause);
        imgClear = findViewById(R.id.img_clear);
        tvTitleSong = findViewById(R.id.tv_title_song);
        tvSingerSong = findViewById(R.id.tv_singer_song);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // unregister Broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
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

    private void handleLayoutMusic(int actionMusic) {
        switch (actionMusic){
            case MyService.ACTION_START:
                layout_bottom.setVisibility(View.VISIBLE);
                showInfoSong();
                setStatusButtonPlayOrPause();
                break;
            case MyService.ACTION_PAUSE:
                setStatusButtonPlayOrPause();
                break;
            case MyService.ACTION_RESUME:
                setStatusButtonPlayOrPause();
                break;
            case MyService.ACTION_CLEAR:
                layout_bottom.setVisibility(View.GONE);
                break;
        }
    }

    private void showInfoSong(){
        if(mSong == null){
            return;
        }
        imgSong.setImageResource(mSong.getImage());
        tvTitleSong.setText(mSong.getTitle());
        tvSingerSong.setText(mSong.getSinger());

        imgPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){
                    sendActionToService(MyService.ACTION_PAUSE);
                }else{
                    sendActionToService(MyService.ACTION_RESUME);
                }
            }
        });

        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToService(MyService.ACTION_CLEAR);
            }
        });

    }

    private void setStatusButtonPlayOrPause(){
        if(isPlaying){
            imgPlayOrPause.setImageResource(R.drawable.ic_pause);
        }else{
            imgPlayOrPause.setImageResource(R.drawable.ic_play);
        }
    }

    private void sendActionToService(int action){
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action_music_service", action);
        startService(intent);
    }

}
