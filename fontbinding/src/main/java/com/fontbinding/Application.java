package com.fontbinding;

import android.content.Context;

/**
 * Created by vivek on 29/04/16.
 */
public class Application extends android.app.Application {
    private static Context context;

    public Application() {
        context = getApplicationContext();
    }


    public static Context getAppContext() {
        return context;
    }
}
