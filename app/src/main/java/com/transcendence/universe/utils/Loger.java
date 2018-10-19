package com.transcendence.universe.utils;

import android.app.Activity;
import android.util.Log;

import java.util.Map;

/**
 * 
 * @Description  Log Util
 * @author  liufeng@solot.com
 * @date 2015年6月2日
 */
public class Loger {

	public static void i(String tag, String str) {
		Log.i(tag, str);
	}
	public static void i(String tag, Map<String, String> map) {
		for (String s : map.keySet()) {
			Loger.i(tag,"key:" + s+" values:" + map.get(s));
		}
	}
	public static void i(String tag,String key, Map<String, String> map) {
		Loger.i(tag,"------"+key);
		for (String s : map.keySet()) {
			Loger.i(tag,"key:" + s+" values:" + map.get(s));
		}
	}
	public static void e(String tag, String str) {
		Log.e(tag, str);
	}

	public static void i(Activity activity, String str) {
		String tag = activity.getLocalClassName();
	     Log.i(tag, str);
	}

	public static void e(Activity activity, String str) {
		String tag = activity.getLocalClassName();
		Log.e(tag, str);
	}
}
