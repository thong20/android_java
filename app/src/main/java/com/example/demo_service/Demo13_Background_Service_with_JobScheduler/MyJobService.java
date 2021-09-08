package com.example.demo_service.Demo13_Background_Service_with_JobScheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {

    public static final String TAG = MyJobService.class.getName();
    private boolean jobCanceled;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Logdln("onStartJob()", 15);

        doBackgroundWork(jobParameters);
        return false; // false: hàm onStopJob() sẽ không được gọi khi ta click
                      // vào Button "Cancel JobScheduler"
                      // true: hàm onStopJob() sẽ được gọi khi ta click button "Cancel JobSheduler"
                      // link: https://developer.android.com/reference/android/app/job/JobService#onStartJob(android.app.job.JobParameters)
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Logdln("onStopJob()", 21);
        jobCanceled = true;
        return true;
    }


    private void doBackgroundWork(final JobParameters jobParameters) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                for(int i = 0 ; i <= 20 ; i++){
                    if (jobCanceled){
                        return;
                    }

                    Logdln("run: " +i, 39 );
                    //
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Chú ý: ta phải đặt biến "jobParameters" là "final"
                // Ở tham số thứ 2: sau khi hoàn thành "job" rồi, thì đặt lại
                // "job" nữa không, true là đồng ý, false là không
                Logdln("finish jobService", 51);
                jobFinished(jobParameters, false);
            }
        }).start();

    }

    // =========================================================
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== MyJobService.java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== MyJobService.java - line: " + n + " ==============================\n" + str);
    }
    public void Logd(String str){
        Log.d("Log.d", "=== MyJobService.java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== MyJobService.java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(String str){
      Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}
