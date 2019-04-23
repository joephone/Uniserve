package com.transcendence.universe.abp.base.util;

import android.app.ActivityManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import com.transcendence.universe.service.AppAplication;
import com.transcendence.universe.utils.Logs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Joephone on 2019/4/19 15:37
 * E-Mail Address：joephonechen@gmail.com
 */

public class DeviceInfoUtil {


    /**
     * 获取android当前可用内存大小
     */
    private String getAvailMemory() {// 获取android当前可用内存大小

        ActivityManager am = (ActivityManager) AppAplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(AppAplication.getInstance(), mi.availMem);// 将获取的内存大小规格化
    }

    /**
     * 获得系统总内存
     */
    private String getTotalMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Logs.logI(str2+num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(AppAplication.getInstance(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * 获取IMEI号，IESI号，手机型号
     */
    public static String getInfo() {
        String str ="";
        TelephonyManager mTm = (TelephonyManager) AppAplication.getInstance().getSystemService(AppAplication.TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();//IMEI 国际移动设备识别码
        String imsi = mTm.getSubscriberId();//IMSI 国际移动用户识别码
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        String release=android.os.Build.VERSION.RELEASE;//系统版本
        String display=android.os.Build.DISPLAY;//版本号  //        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得

        mtype= mtype.replace(" ","");//去掉空格
        Logs.i("手机IMEI号："+imei
                +"\n手机IMSI号：" +imsi
                +"\n手机型号："+mtype
                +"\n系统版本："+release
                +"\n版本号："+display
                +"\n手机品牌："+mtyb
                );  //+"\n手机号码："+numer
        str = "手机IMEI号："+imei
                +"\n手机IMSI号：" +imsi
                +"\n手机型号："+mtype
                +"\n系统版本："+release
                +"\n版本号："+display
                +"\n手机品牌："+mtyb;
        return str;


    }
    /**
     * .获取手机MAC地址
     * 只有手机开启wifi才能获取到mac地址
     */
//    private String getMacAddress(){
//        String result = "";
//        WifiManager wifiManager = (WifiManager) AppAplication.getInstance().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        result = wifiInfo.getMacAddress();
//        Logs.logI("手机macAdd:" + result);
//        return result;
//    }
    /**
     * 手机CPU信息
     */
    private String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Logs.logI( "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }


    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        Logs.logI("version:"+android.os.Build.VERSION.RELEASE);
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        Logs.logI("brand:"+android.os.Build.BRAND);
        return android.os.Build.BRAND;
    }

//    /**
//     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
//     *
//     * @return  手机IMEI
//     */
//    public static String getIMEI(Context ctx) {
//        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
//        if (tm != null) {
//            return tm.getDeviceId();
//        }
//        return null;
//    }

}
