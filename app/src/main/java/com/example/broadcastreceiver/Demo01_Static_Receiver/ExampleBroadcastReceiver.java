package com.example.broadcastreceiver.Demo01_Static_Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Nếu "intent.getAction()" == Connectivity.CONNECTIVITY_ACTION thì
        // chúng ta đã lắng nghe được sự thay đổi của Network
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if(isNetworkAvailable(context)){
                showToast(context, "thong20 - Internet Connected");
            }else{
                showToast(context, "thong20 - Internet Disconnected");
            }
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null){
            return false;
        }

        // Từ Android 6+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Khi gọi hàm .getActiveNetwork(), nếu bị báo đỏ, ta phải thêm
            // permission "ACCESS_NETWORK_STATE" vào "AndroidManifest.xml"
            // hoặc nhấn Alt+Enter để xem gợi ý
            Network network = connectivityManager.getActiveNetwork();
            if(network == null){
                return false;
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }else{
            // Khi gọi hàm .getActiveNetworkInfo() ta phải thêm
            // permission "ACCESS_NETWORK_STATE" vào "AndroidManifest.xml"
            // hoặc nhấn Alt+Enter để xem gợi ý
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        }
    }





    // ====================================================================
    public void Logd(String str){
        Log.d("Log.d", "=== .java ==============================\n" + str);
    }
    public void Logdln(String str, int n){
        Log.d("Log.d", "=== .java - line: " + n + " ==============================\n" + str);
    }
    public static void LogdStatic(String str){
        Log.d("Log.d", "=== .java ==============================\n" + str);
    }
    public static void LogdlnStatic(String str, int n){
        Log.d("Log.d", "=== .java - line: " + n + " ==============================\n" + str);
    }
    public void showToast(Context context, String str ){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
