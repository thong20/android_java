package com.example.demo_service.Demo11_Control_Music_with_Foreground_and_Bound_Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_service.R;

public class Main_Demo11 extends AppCompatActivity {

    private RelativeLayout layoutBottom;
    private TextView tvNameSong;
    private ImageView imgPlayOrPause;

    private MyService myService;
    private boolean isServiceConnected;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            myService = myBinder.getMyService();
            isServiceConnected = true;

            // Khi ràng buộc thành công
            handleLayoutMusic();
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
        setContentView(R.layout.activity_main_demo11);

        Button btnStartService  = findViewById(R.id.btn_start_service);
        Button btnStopService   = findViewById(R.id.btn_stop_service);

        layoutBottom    = findViewById(R.id.layout_bottom);
        tvNameSong      = findViewById(R.id.tv_name_song);
        imgPlayOrPause  = findViewById(R.id.img_play_or_pause);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopService();

                layoutBottom.setVisibility(View.GONE);
            }
        });

        imgPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myService.isPlaying()){
                    myService.pauseMusic();
                }else{
                    myService.resumeMusic();
                }

                setStatusImageViewPlayOrPause();
            }
        });

    }



    private void handleLayoutMusic(){
        layoutBottom.setVisibility(View.VISIBLE);
        tvNameSong.setText(myService.getSong().getName());

        setStatusImageViewPlayOrPause();
    }

    private void setStatusImageViewPlayOrPause(){
        if(myService == null){
            return;
        }
        if(myService.isPlaying()){
            imgPlayOrPause.setImageResource(R.drawable.ic_pause);
        }else {
            imgPlayOrPause.setImageResource(R.drawable.ic_play);
        }
    }

    private void onClickStartService() {
        Intent intent = new Intent(this, MyService.class);

        Song song = new Song("thong20", R.raw.somewhere_somehow);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", song);
        intent.putExtras(bundle);

        startService(intent);
        // Ràng buộc cho Bound Service
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onClickStopService() {
        // STOP FOREGROUND SERVICE
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);

        // STOP BOUND SERVICE
        if(isServiceConnected){
            unbindService(mServiceConnection);
            isServiceConnected = false;
        }
    }



}