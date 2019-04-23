package com.transcendence.universe.utils.system;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.universe.service.AppAplication;


public class ToastHelper {
	static ToastHelper instance;
	static int top = 60;
	private Toast mToast;
	private Toast topToast;
	View toastRoot;
	TextView textView;

	/*
	 * 单例模式中获取唯一的Utli实例
	 */
	public static ToastHelper getInstance() {
		if (null == instance) {
			instance = new ToastHelper();
			Context context = AppAplication.getInstance();
			if (context!=null) {
				top = DensityUtils.dip2px(context, 60);
			}else {
				top=100;
			}
			
		}
		return instance;
	}

	
	public void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
		if (topToast != null) {
			topToast.cancel();
		}
	}

	/**
	 * 
	 * @Title: show
	 * @Description: TODO
	 * @param info
	 * @param time
	 *            1 代表长时间 , 0 代表短时间
	 */
	public void showToast(int info, int time) {
		Context context = AppAplication.getInstance();
		String infoStr="";
		if (context!=null) {
			 infoStr = context.getString(info);	
		}
		showToast(infoStr,time);

	}
	
	/**
	 * 
	 * @Title: show
	 * @Description: TODO
	 * @param info
	 * 默认0 代表短提示
	 */
	public void showToast(int info) {
		showToast(info,0);
	}

	public void showToast(String str) {
		showToast(str,0);
	}
	
	public void showToast(String infoStr, int time) {
		Context context = AppAplication.getInstance();
		if (mToast == null && context != null) {
			mToast = Toast.makeText(context, infoStr, time);
		} else {
			mToast.setText(infoStr);
			mToast.setDuration(time);
		}
		mToast.show();
	}

	/**
	 *
	 * @param msg
	 *            内容
	 * @param loction
	 *            显示的位置
	 */
	public void ToastMessage(String msg, int loction) {
		Context cont = AppAplication.getInstance();
		if (loction == Gravity.TOP) {// 上方
			Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, top);
			toast.show();
		} else if (loction == Gravity.CENTER) {// 中间
			Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else if (loction == Gravity.BOTTOM) {// 默认显示下方
			Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}