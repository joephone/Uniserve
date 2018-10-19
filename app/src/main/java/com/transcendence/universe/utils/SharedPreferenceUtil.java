package com.transcendence.universe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {
	/** 用于文本信息的存储 */
	SharedPreferences preferences;

	/**
	 * 无参的构造方法
	 */
	public SharedPreferenceUtil() {
	}

	/**
	 * 带1个参的构造方法
	 */
	public SharedPreferenceUtil(Context context) {
		preferences = context.getSharedPreferences("BatteryInfo",
				Context.MODE_PRIVATE);
	}

	/**
	 * 带2个参的构造方法
	 */
	public SharedPreferenceUtil(Context context, String name) {
		preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	/**
	 * 保存数据的重载方法
	 */
	public void save(String key, String value) {
		Editor editor = preferences.edit();
		editor.putString(key, value);
		//提交，必须有
		editor.commit();
	}

	public void save(String key, int value) {
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public void save(String key, boolean value) {
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 获取数据的方法
	 */
	public String getString(String key) {
		return preferences.getString(key, null);
	}

	public int getInt(String key) {
		return preferences.getInt(key, 0);
	}

	public boolean getBoolean(String key) {
		return preferences.getBoolean(key, false);
	}
}