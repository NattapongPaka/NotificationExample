package com.example.paka.myfirebasedemo.activity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;

import com.example.paka.myfirebasedemo.util.Util;

import timber.log.Timber;

/**
 * Created by Noth on 8/7/2559.
 */

public class ReplyIntentService extends IntentService {

    private String ACTION_DIALOG_START = "com.intent.action.start_dialog";
    private String ACTION_DIALOG_HIDDEN = "com.intent.action.hidden_dialog";
    private Context context;



    public ReplyIntentService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        //if (!Util.isMyServiceRunning(ReplyDialogService.class, context)) {
            Timber.d("isMyService_NotRunning");
            Timber.d("isAction== "+ intent.getAction().toString());
            Timber.d("isExtra== "+intent.getExtras().toString());

            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            CharSequence msgResult = remoteInput.getCharSequence("key_text_reply");
            Timber.d("Message : "+msgResult);

            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Timber.d("ACTION_BOOT_COMPLETED");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Timber.d("ACTION_SCREEN_OFF");
            } else if (intent.getAction().equals(ACTION_DIALOG_START)){
                Timber.d("ACTION_DIALOG_START");
            } else if (intent.getAction().equals(ACTION_DIALOG_HIDDEN)){
                Timber.d("ACTION_DIALOG_HIDDEN");
            }
        //}
    }
}
