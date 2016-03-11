package com.example.vladimirkarassouloff.projetter;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vladimir on 16/02/2016.
 */
public class MyApp extends Application {
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}