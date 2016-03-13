package com.example.vladimirkarassouloff.projetter.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vladimir on 16/02/2016.
 */
public class MyApp extends Application {
    public static Context context;
    public static MyApp myApp;
    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        myApp = this;
    }
}