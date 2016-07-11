package com.example.paka.myfirebasedemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.RemoteInput;

import com.example.paka.myfirebasedemo.util.Util;

import timber.log.Timber;

/**
 * Created by Noth on 6/7/2559.
 */

public class ReplyReciver extends BroadcastReceiver {

    private String ACTION_DIALOG_START = "com.intent.action.start_dialog";
    private String ACTION_DIALOG_HIDDEN = "com.intent.action.hidden_dialog";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent != null) {
                Timber.d("isAction== '%s'", intent.getAction());
                Timber.d("isExtra== '%s'",intent.getStringExtra(ACTION_DIALOG_START));
                Timber.d("isStringExtra == '%s'",intent.getStringExtra("Test"));
                Timber.d("isStringExtra == '%s'",intent.getStringExtra("Test2"));
                Timber.d("isStringData == '%s'",intent.getDataString());
//                Bundle mBundle = intent.getExtras();
//                Timber.d("isBundle == '%s'",mBundle.get("Test2"));
            }
            if (!Util.isMyServiceRunning(ReplyDialogService.class, context)) {
                Timber.d("isMyService_NotRunning");
                Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
                CharSequence msgResult = remoteInput.getCharSequence("key_text_reply");
                Timber.d("Message : '%s'",msgResult);
                Timber.d("Bundle : '%s' '%s'",remoteInput.getString("Test3"),remoteInput.getString("Test4"));

                if (getAction(intent, Intent.ACTION_BOOT_COMPLETED)) {
                    Timber.d("ACTION_BOOT_COMPLETED");
                } else if (getAction(intent, Intent.ACTION_SCREEN_OFF)) {
                    Timber.d("ACTION_SCREEN_OFF");
                } else if (getAction(intent, ACTION_DIALOG_START)) {
                    Timber.d("ACTION_DIALOG_START");
                } else if (getAction(intent, ACTION_DIALOG_HIDDEN)) {
                    Timber.d("ACTION_DIALOG_HIDDEN");
                }
            } else {
                Timber.d("isMyService_Running");
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private boolean getAction(Intent intent, String messageAction) {
        try {
            if (intent.getAction().equals(messageAction)) {
                return true;
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            ;
        }
        return false;
    }
}
