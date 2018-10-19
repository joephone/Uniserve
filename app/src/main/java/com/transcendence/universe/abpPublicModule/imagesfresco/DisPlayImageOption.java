package com.transcendence.universe.abpPublicModule.imagesfresco;

import android.util.Log;


import com.transcendence.universe.utils.StringUtils;


public class DisPlayImageOption {
	static String tag = "DisPlayImageOption";
	static DisPlayImageOption instance;
	static boolean isWebp = false;
	static String ext = ".webp";

	/*
	 * 单例模式中获取唯一的Utli实例
	 */
	public static DisPlayImageOption getInstance() {
		if (null == instance) {
			instance = new DisPlayImageOption();
			isWebp = iswbmp();
		}
		return instance;
	}




	public static boolean iswbmp() {
		boolean ret = false;
		String vString = android.os.Build.VERSION.RELEASE;
		String[] vArray = vString.split("\\.");
		Log.i(tag, "vString:" + vString);
		Log.i(tag, "vArray[0]:" + vArray[0]);
		if (StringUtils.toInt(vArray[0]) >= 4&&StringUtils.toInt(vArray[1])>=3) {
			if (StringUtils.toInt(vArray[0]) == 4&&StringUtils.toInt(vArray[1])<=3){
				ret = false;
			}else {
				ret = true;
			}
		}
		String str0 = android.os.Build.MANUFACTURER;
		if (str0.contains("Nokia") || str0.contains("NOKIA")) {
			ret = false;
		}
		Log.i(tag, "ret:" + ret);
		return ret;
	}

	public static boolean iswbmpPng() {
		boolean ret = false;
		String vString = android.os.Build.VERSION.RELEASE;
		String[] vArray = vString.split("\".\"");
		if (StringUtils.toInt(vArray[0]) >= 4
				&& StringUtils.toInt(vArray[1]) >= 3) {
			ret = true;
		}
		return ret;
	}




}
