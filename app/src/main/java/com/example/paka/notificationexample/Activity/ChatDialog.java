package com.example.paka.notificationexample.Activity;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.paka.notificationexample.ChatDialogFragment;
import com.example.paka.notificationexample.R;
import com.example.paka.notificationexample.Utily.Utily;

/**
 * Created by Noth on 21/6/2559.
 */
public class ChatDialog extends AppCompatActivity {

    private String TAG = ChatDialog.class.getSimpleName();
    private FrameLayout frameLayout;
    private String title;
    private String group_id;
    private int notification_size;
    private String cal_id;
    int in_mNotificationID;
    private MyBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.chat_dialog);
        String in_group_id = getIntent().getStringExtra("group_id");
        String in_title = getIntent().getStringExtra("title");
        in_mNotificationID = getIntent().getIntExtra("notificationID", -1);
        mBroadcastReceiver = new MyBroadcastReceiver();
        try {
            Log.i("ChatDialog", "GroupID : " + in_group_id + " :Title: " + in_title + " : " + String.valueOf(in_mNotificationID));
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            title = preferences.getString("TITLE", "");
            group_id = preferences.getString("group_id", "");
            cal_id = preferences.getString("cal_id", "");

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(in_mNotificationID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /**
         * Android less then 23 22 21 ...
         */
        if (!Utily.isAndroidVersionN()) {
            //setContentView(R.layout.chat_dialog);
//            Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//            ActionBar mActionBar = getSupportActionBar();
//            if (mToolbar != null && mActionBar != null) {
//                setSupportActionBar(mToolbar);
//                mActionBar.setDisplayShowCustomEnabled(true);
//                mActionBar.setDisplayHomeAsUpEnabled(true);
//                mToolbar.setTitle(in_title);
//            }
            DialogFragment dialogFragment = ChatDialogFragment.newInstance(title, group_id, cal_id);
            dialogFragment.show(getSupportFragmentManager(),"ChatDialogFragment");
        } else {
            /**
             * Android API 23 more then
             */
            try {
                Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                CharSequence msgResult = remoteInput.getCharSequence("key_text_reply");
                if (msgResult != null && msgResult.length() > 0) {
//                    Random r = new Random();
//                    String messageValue = "";
//                    String lat = "";
//                    String lng = "";
//                    String chat_id = String.valueOf(r.nextInt(9999999));
//                    MessageManager messageManager = new MessageManager(this);
//                    messageManager.sendMessageToServer(messageValue, lat, lng, chat_id, group_id, title);
                    Log.i("ChatDialog", "MSG : " + msgResult.toString());
                } else {
                    Log.i("ChatDialog", "MSG Length == 0");
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(in_mNotificationID);
            //finish();
        }
        registerInteneFilter();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

    private void registerInteneFilter(){
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
        mIntentFilter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mBroadcastReceiver,mIntentFilter);
    }
//
//    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
//                Log.i(TAG,"ScreenOk");
////                AlertDialog alertDialog = new AlertDialog.Builder(context)
////                        .setView(R.layout.notification_box)
////                        .create();
////                alertDialog.getWindow().setType(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
////                alertDialog.show();
//
////                DialogFragment dialogFragment = ChatDialogFragment.newInstance(title, group_id, cal_id);
////                //dialogFragment.getDialog().getWindow().setType(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
////                dialogFragment.show(getSupportFragmentManager(),"ChatDialogFragment");
//
//            }else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
//                Log.i(TAG,"ScreenOff");
//            }else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
//                Log.i(TAG,"ScreenPresent");
//            }
//        }
//    };

    @Override
    public void onAttachedToWindow() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
}
