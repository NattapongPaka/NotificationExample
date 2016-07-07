package com.example.paka.myfirebasedemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.paka.myfirebasedemo.util.Util;

import timber.log.Timber;

/**
 * Created by Noth on 6/7/2559.
 */

public class ReplyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Util.isMyServiceRunning(ReplyDialogService.class, context)) {
            Timber.d("isMyService_NotRunning");
            Timber.d("isAction== '%s'", intent.getAction());
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Timber.d("ACTION_BOOT_COMPLETED");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Timber.d("ACTION_SCREEN_OFF");
            }
        } else {
            Timber.d("isMyService_Running");
        }
    }
}
