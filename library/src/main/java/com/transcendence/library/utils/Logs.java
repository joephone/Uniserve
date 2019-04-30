package com.transcendence.library.utils;

import android.util.Log;
import com.transcendence.library.global.Global;


/**
 * Created by Administrator on 2017/9/7.
 */

public class Logs {

    //如果打包  就将isTest设置为false
    public static void logI(String content) {
        if (Global.isTest) {
            Log.i(Global.TAG, content);
        }
    }



    public static void logE(String content) {
        if (Global.isTest) {
            Log.e(Global.TAG, content);
        }
    }

    public static void logV(String content) {
        if (Global.isTest) {
            Log.v(Global.TAG, content);
        }
    }

}
