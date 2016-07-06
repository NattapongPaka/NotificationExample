package com.example.paka.myfirebasedemo.util;

import android.content.Context;

/**
 * Created by PONG on 1/19/16 AD.
 */
public class Contextor {
    private static Contextor instantce;

    public static Contextor getInstantce() {
        if (instantce == null) {
            instantce = new Contextor();
        }
        return instantce;
    }

    private Context mContext;

    private Contextor() {
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}
