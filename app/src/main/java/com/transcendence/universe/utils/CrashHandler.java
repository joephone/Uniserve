package com.transcendence.universe.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.transcendence.universe.R;
import com.transcendence.universe.service.MyApplication;

/**
 * Created by Joephone on 2018/10/26 11:30
 * E-Mail Address：joephonechen@gmail.com
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";
    private static CrashHandler INSTANCE = new CrashHandler();
    private Context mContext;
    @SuppressWarnings("unused")
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        new Thread() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyApplication.getInstance());
                builder.setIcon(R.drawable.universe_icon).setTitle(R.string.app_name)
                        .setMessage("出了点小意外")
                        .setPositiveButton("重新启动", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent i = mContext.getPackageManager()
                                        .getLaunchIntentForPackage(
                                                mContext.getPackageName());
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                mContext.startActivity(i);
//                                App.getInstance2().exit();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        }.start();
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    @SuppressWarnings("unused")
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        return true;
    }

}
