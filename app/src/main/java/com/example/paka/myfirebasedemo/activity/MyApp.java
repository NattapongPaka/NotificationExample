package com.example.paka.myfirebasedemo.activity;

import android.app.Application;
import android.content.Context;

import com.example.paka.myfirebasedemo.util.Contextor;

/**
 * Created by Noth on 6/7/2559.
 */

public class MyApp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstantce().init(getApplicationContext());
    }


}
