package com.example.demo_service.Demo13_Background_Service_with_JobScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo_service.R;

public class Main_Demo13 extends AppCompatActivity {

    private static final int JOB_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo13);

        Button btnStartScheduler = findViewById(R.id.btn_start_scheduler);
        Button btnCancelScheduler = findViewById(R.id.btn_cancel_scheduler);

        btnStartScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartScheduler();
            }
        });

        btnCancelScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCancelScheduler();
            }
        });

    }

    private void onClickStartScheduler() {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
                // Biến jobInfo có giá trị khi thiết bị được cắm sạc
//                .setRequiresCharging(true)
                // Biến jobInfo có giá trị khi thiết bị được kết nối mạng
                // và loại mạng phải là:
                // - NETWORK_TYPE_NONE
                // - NETWORK_TYPE_ANY
                // - NETWORK_TYPE_UNMETERED - Kiểu Wifi
                // - NETWORK_TYPE_NOT_ROAMING
                // - NETWORK_TYPE_CELLULAR
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                // Biến jobInfo vẫn có giá trị khi thiết bị bị sập nguồn và khởi động lại
                // dẫn đến JobService của chúng ta vẫn hoạt động bình thường
                // Bắt buộc ta phải khai báo permission trong "AndroidManifest.xml"
                // là "android.permission.RECEIVE_BOOT_COMPLETE"
                .setPersisted(true)
                // Lặp lại service trong khoảng thời gian là millisecond
                // và giá trị tối thiểu là 15 phút <=> 900 s <=> 900.000 ms
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

    private void onClickCancelScheduler() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        // Ta phải truyền đúng ID của "job" cần cancel
        jobScheduler.cancel(JOB_ID);
    }
}