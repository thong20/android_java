package com.example.broadcastreceiver.Demo03_Charge_Battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // for Android 6+ - API 23
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Trick By Google
            // Are we charging / charged?
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            // How are we charging?
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            // Power source is an USB Port
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            // Power source is an AC charger
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;


            MainDemo03.setResult(isCharging, chargePlug, usbCharge, acCharge);
        }
        // for Android 6-
        else{

        }
    }
}
