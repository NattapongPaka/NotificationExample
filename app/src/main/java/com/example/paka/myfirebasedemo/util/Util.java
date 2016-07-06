package com.example.paka.myfirebasedemo.util;

import android.app.ActivityManager;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.paka.myfirebasedemo.activity.MyApp;

/**
 * Created by Noth on 29/6/2559.
 */
public class Util {

    public static boolean isAndroidVersionN(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLockScreen(){
        Context context = Contextor.getInstantce().getContext();
        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if( myKM.inKeyguardRestrictedInputMode()) {
            //it is locked
            return true;
        } else {
            //it is not locked
            return false;
        }
    }

    public static boolean isNotificationReplyOn(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Contextor.getInstantce().getContext());
        String replyStatus = preferences.getString("replyStatus","");
        if(replyStatus != null){
            if(replyStatus.equals("On")){
                return true;
            }
        }
        return false;
    }

    //private static String LOG_TAG = ServiceTools.class.getName();

    public static boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager)context. getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service already","running");
                return true;
            }
        }
        Log.i("Service not","running");
        return false;
    }




}
