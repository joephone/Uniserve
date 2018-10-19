package com.transcendence.universe.utils;

import android.util.Log;

/**
 * Created by Joephone on 2018/10/18 17:37
 * E-Mail Address：joephonechen@gmail.com
 */

public class Logs {


    //如果打包  就将isTest设置为false
    public static void logI(String content) {
//        if (Global.isTest) {
            Log.i(Global.TAG, content);
//        }
    }

    public static void logJson(String content) {
//        if (Global.isTest) {
//            Logger.json(content);
//        }
    }

    public static void logE(String content) {
//        if (Global.isTest) {
        Log.e(Global.TAG, content);
//        }
    }

    public static void logV(String content) {
//        if (Global.isTest) {
            Log.v(Global.TAG, content);
//        }
    }


    public static void d(String content) {
//        if (Global.isTest) {
        Log.d(Global.TAG, content);
//        }
    }
}
