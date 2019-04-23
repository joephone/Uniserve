package com.transcendence.library.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Joephone on 2019/4/23 16:31
 * E-Mail Address：joephonechen@gmail.com
 */

public class LibApplication extends Application {

    private static Context applicationContext;
    private static LibApplication instance;

    public static Context getAppContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();

    }

    public static LibApplication getInstance() {
        if (instance == null) {
            instance = new LibApplication();
        }
        return instance;
    }

}
