package com.transcendence.universe.service;

import android.app.Application;
import android.content.Context;


public class MyApplicaton extends Application {
    private String tag = "MyApplicaton";
    public static MyApplicaton instance;
    // 单例模式中获取唯一的MyApplication实例
    static Context context;

    public static MyApplicaton getInstance() {
        if (null == instance) {
            instance = new MyApplicaton();
        }
        return instance;
    }

    public Context getContext() {
        context = getApplicationContext();
        return getApplicationContext();
    }

    public int count = 0;


    private void initImage() {
//        ImagePipelineConfig config = ImagePipelineConfigUtil
//                .getDefaultImagePipelineConfig(getApplicationContext(), new OkHttpClient());
//        Fresco.initialize(getApplicationContext(), config);
    }

    public void logOut(boolean showAct) {
        //用户退出
//        CounectServiceSocket.getInstance().closeSocket();
//        AppCache.getInstance().cleralogin();
//        if (showAct) {
//            Intent mIntent = new Intent(BroadcastKey.LOGIN_OUT);
//            context.sendBroadcast(mIntent);
//        }
    }


}
