package com.example.mubasharhussain.broadcastreceiver_sharedpreference_notification;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.Settings;

/**
 * Created by MubasharHussain on 12/30/2017.
 */

public class MyReceiver {
    Boolean isAirplaneEnabled;
    WifiManager wifiManager;
    Boolean isWifiEnabled;
    String BatteryStatus;

    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context, "OnReceive", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            BatteryStatus = "Low";
        }
        if (intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {
            BatteryStatus = "Okay";
        }

        isAirplaneEnabled = Settings.Global.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        isWifiEnabled = wifiManager.isWifiEnabled();

        CustomMessageEvent customMessageEvent = new CustomMessageEvent();
        customMessageEvent.setbBoolean(isWifiEnabled);
        EventBus.getDefault().post(customMessageEvent);
        customMessageEvent.setaBoolean(isAirplaneEnabled);
        EventBus.getDefault().post(customMessageEvent);
        customMessageEvent.setS1(BatteryStatus);
        EventBus.getDefault().post(customMessageEvent);
    }
}
