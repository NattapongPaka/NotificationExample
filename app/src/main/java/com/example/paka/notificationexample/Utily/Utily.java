package com.example.paka.notificationexample.Utily;

import android.os.Build;

/**
 * Created by Noth on 29/6/2559.
 */
public class Utily {

    public static boolean isAndroidVersionN(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return true;
        } else {
            return false;
        }
    }

}
