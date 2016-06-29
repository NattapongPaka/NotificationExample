package com.example.paka.notificationexample.Activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Noth on 29/6/2559.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyBroadcastReceiver.class.getSimpleName();
    private Context mContext;

    private OverlayService overlayService;
    private Boolean mBoundService;

    public MyBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "[onReceive]" + action);
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            //showDialog("Esto es una prueba y se levanto desde");
        } else if (action.equals(Intent.ACTION_USER_PRESENT)) {
            //hideDialog();
        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
           // hideDialog();
        }
    }

//    public ServiceConnection mServiceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder service) {
//            overlayService = ((OverlayService.LocalBinder)service).getService();
//            mBoundService = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//            mBoundService = false;
//            overlayService = null;
//        }
//    };

    private void doBindService(){

    }

    private void unBindService(){

    }

}
