package com.example.demo_service.Demo09_BoundService_Messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.example.demo_service.R;

public class Main_Demo09 extends AppCompatActivity {

    private Messenger mMessenger;
    private boolean isServiceConnected;

    // Tạo ServiceConnection cho đối số thứ 2 của hàm bindService()
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            mMessenger = new Messenger(iBinder);
            isServiceConnected = true;

            // Send message play music
            sendMessagePlayMusic();
        }

        // Hàm onServiceDisconnected() chỉ chạy khi service bị kill
        // do thiếu tài nguyên, hoặc lý do gì đó mà hệ thống sẽ kill service
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
            isServiceConnected = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo09);

        Button btnStartService = findViewById(R.id.btn_start_service);
        Button btnStopService = findViewById(R.id.btn_stop_service);

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
            }
        });
    }

    private void sendMessagePlayMusic() {
        Message message = Message.obtain(null, MusicService.MSG_PLAY_MUSIC, 0, 0);
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void onClickStartService() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onClickStopService() {
        // Để stop 1 service, khi và chỉ khi service đó đã connected
        // Kiểm tra 1 service đã connected chưa?
        if(isServiceConnected){
            // Gỡ tất cả các ràng buộc
            unbindService(mServiceConnection);
            // Vì khi ta gọi hàm unbindService() thì
            // hàm onServiceDisconnected() sẽ không thực thi
            // và biến isServiceConnected sẽ không được gán thành false
            // vì vậy ta phải gán thủ công cho biến isServiceConnected = false
            // Còn hàm onServiceDisconnected() chỉ chạy khi service bị kill
            // do thiếu tài nguyên, hoặc lý do gì đó mà hệ thống sẽ kill service
            // thì onServiceDisconnected() mới chạy
            isServiceConnected = false;
        }
    }
}