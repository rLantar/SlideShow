package com.example.lantar.solveasttest;

/**
 * Created by Lantar on 02.12.2015.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isCharging = false;
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if (status == 2)
            isCharging = true;

        Toast.makeText(context, "Status : " + status + "\nCharging : " + isCharging, Toast.LENGTH_SHORT).show();

        if (isCharging) {
            Intent i = new Intent(context, SlideShowActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

}