package com.transcendence.universe.util;

import android.os.Environment;

import com.transcendence.universe.service.MyApplicaton;

/**
 * Created by joephone on 2016/3/7.
 */
public class Global {

    public static final String TAG = "universe";

    public static final int WELCOME_TIME = 5000;

    public static final String DB_NAME = "catches_db";
    public static final String FISHDB_NAME = "fish_db";
    public static final String FISHDB_DIR = "fish/" ;
    public static final String dbDir = "/data/data/" + MyApplicaton.getInstance().getPackageName() + "/databases/";
    public static final String USER_QR = "https://testapi.solot.co/" ;
    public static final int STANDART_ZOOM = 16;
    public static final int SLIDINGUPPANEL_SLIDING_VELOCITY = 500;
    public static final int SHOW_MAP_ZOOM = 13;
    public static final String LOCAL_PATH = Environment.getExternalStorageDirectory() + "/DCIM/Camera" ;
    public static final String CUT_PATH_TEMP = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/catches/cut/Temp";;
    public  final  static  class VOIDE{
        //小视屏
        public static String cmdline = "ffmpeg -threads 4 -y -i %1$s -strict -2 -vf transpose=1,crop=480:360:0:0 -preset ultrafast -tune zerolatency -s 480x360 -b:v "
                + "800k -r 24 -metadata:s:v rotate=0 -vcodec libx264 -acodec copy %2$s";
        //只压缩  1源文件，2角度。3输出
        public static String test= "ffmpeg -threads 4 -y -i %1$s -b:v 800k -r 24 -vcodec libx264 -acodec copy %2$s";
        public static String cmdAudioCop = "ffmpeg -threads 4 -y -i %1$s -vcodec libx264  -acodec copy %2$s";
//      public static String cmdAudioCop = "ffmpeg -threads 4 -y -i %1$s -vcodec libx264 -preset superfast -crf 24 -acodec copy -s %2$s %3$s";
    }


}
