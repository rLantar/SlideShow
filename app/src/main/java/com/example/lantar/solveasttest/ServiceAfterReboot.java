package com.example.lantar.solveasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Lantar on 02.12.2015.
 */
public class ServiceAfterReboot extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Intent startSlideShowIntent = new Intent(context, SlideShowActivity.class);
            startSlideShowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(startSlideShowIntent);
        }
    }
}
