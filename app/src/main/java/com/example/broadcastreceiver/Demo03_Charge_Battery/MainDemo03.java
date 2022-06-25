package com.example.broadcastreceiver.Demo03_Charge_Battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.broadcastreceiver.R;

public class MainDemo03 extends AppCompatActivity {

    private static TextView tv_charge_status,
                            tv_charge_plug,
                            tv_usb_charge,
                            tv_ac_charge;

    private MyReceiver myReceiver;
    private IntentFilter myIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo03);

        tv_charge_status = findViewById(R.id.demo03_tv_charge_status);
        tv_charge_plug = findViewById(R.id.demo03_tv_charge_plug);
        tv_usb_charge = findViewById(R.id.demo03_tv_usb_charge);
        tv_ac_charge = findViewById(R.id.demo03_tv_ac_charge);

        myReceiver = new MyReceiver();

        myIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(myReceiver, myIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(myReceiver);
    }

    public static void setResult(boolean isCharging, int chargePlug, boolean isUsbCharge, boolean isACCharge){
        if(isCharging) {
            tv_charge_status.setTextColor(Color.BLUE);
        }else{
            tv_charge_status.setTextColor(Color.RED);
        }

        if(chargePlug > 0) {
            tv_charge_plug.setTextColor(Color.BLUE);
        }else{
            tv_charge_plug.setTextColor(Color.RED);
        }

        if(isUsbCharge) {
            tv_usb_charge.setTextColor(Color.BLUE);
        }else{
            tv_usb_charge.setTextColor(Color.RED);
        }

        if(isACCharge) {
            tv_ac_charge.setTextColor(Color.BLUE);
        }else{
            tv_ac_charge.setTextColor(Color.RED);
        }


        tv_charge_status.setText(isCharging +"");
        tv_charge_plug.setText(chargePlug +"");
        tv_usb_charge.setText(isUsbCharge +"");
        tv_ac_charge.setText(isACCharge +"");
    }
}