/**
 * 
 */
package com.transcendence.universe.abp.base.battery.act;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.SharedPreferenceUtil;


public class BatteryFixActivity extends TitleBarActivity {
	private BatteryManagerActivity mMgrBatActivity;
	private SharedPreferenceUtil preferencesUtil;
	private TextView mTxtHealth, mTxtStatus, mTxtLevel, mTxtQuick, mTxtRec,
			mTxtSlow;
	private ImageView mImgLevel, mImgQuick, mImgRec, mImgSlow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery_fix);
		init();
		getInfo();
	}

	/**
	 * 初始化控件
	 */
	public void init() {
		mMgrBatActivity = new BatteryManagerActivity();
		preferencesUtil = new SharedPreferenceUtil(this);

		mTxtHealth = (TextView) findViewById(R.id.bat_health);
		mTxtStatus = (TextView) findViewById(R.id.bat_level_sta);
		mTxtLevel = (TextView) findViewById(R.id.bat_level);
		mTxtQuick = (TextView) findViewById(R.id.txt_quick_charge);
		mTxtRec = (TextView) findViewById(R.id.txt_recycle_charge);
		mTxtSlow = (TextView) findViewById(R.id.txt_keep_charge);
		mImgLevel = (ImageView) findViewById(R.id.bat_sta_img);
		mImgQuick = (ImageView) findViewById(R.id.img_quick_charge);
		mImgRec = (ImageView) findViewById(R.id.img_recycle_charge);
		mImgSlow = (ImageView) findViewById(R.id.img_keep_charge);
	}

	/**
	 * 获取信息
	 */
	public void getInfo() {
		if (preferencesUtil.getInt("health") == 2) {
			mTxtHealth.setText("电池正常使用中");
		} else {
			mTxtHealth.setText("电池使用情况异常");
		}
		mTxtStatus.setText(preferencesUtil.getString("status"));
		final int curLevel = preferencesUtil.getInt("curLecel");
		mTxtLevel.setText(curLevel + "%");
		if (preferencesUtil.getString("status").equals("充电状态")) {
			mImgLevel.setImageResource(R.drawable.battery_charging);
			if ((curLevel <= 80) && (curLevel >= 0)) {
				mImgQuick.setImageResource(R.drawable.battery_bulb_active);
				mTxtQuick.setText(getResources().getString(
						R.string.quick_charge_run));
				mTxtQuick.setTextColor(Color.GREEN);
				mImgRec.setImageResource(R.drawable.battery_bulb_deactive);
				mTxtRec.setText(getResources().getString(
						R.string.recycle_charge_wait));
				mTxtRec.setTextColor(Color.BLACK);
				mImgSlow.setImageResource(R.drawable.battery_bulb_deactive);
				mTxtSlow.setText(getResources().getString(
						R.string.keep_charge_wait));
				mTxtSlow.setTextColor(Color.BLACK);
			} else if ((curLevel <= 95) && (curLevel > 80)) {
				mImgRec.setImageResource(R.drawable.battery_bulb_active);
				mTxtRec.setText(getResources().getString(
						R.string.recycle_charge_run));
				mTxtRec.setTextColor(Color.GREEN);
				mImgQuick.setImageResource(R.drawable.battery_bulb_deactive);
				mTxtQuick.setText(getResources().getString(
						R.string.quick_charge_over));
				mTxtQuick.setTextColor(Color.WHITE);
				mImgSlow.setImageResource(R.drawable.battery_bulb_deactive);
				mTxtSlow.setText(getResources().getString(
						R.string.keep_charge_wait));
				mTxtSlow.setTextColor(Color.BLACK);
			} else if ((curLevel <= 100) && (curLevel > 95)) {
				mImgSlow.setImageResource(R.drawable.battery_bulb_active);
				mTxtSlow.setText(getResources().getString(
						R.string.keep_charge_run));
				mTxtSlow.setTextColor(Color.GREEN);
				mImgQuick.setImageResource(R.drawable.battery_bulb_deactive);
				mTxtQuick.setText(getResources().getString(
						R.string.quick_charge_over));
				mTxtQuick.setTextColor(Color.WHITE);
				mImgRec.setImageResource(R.drawable.battery_bulb_deactive);
				mTxtRec.setText(getResources().getString(
						R.string.recycle_charge_over));
				mTxtRec.setTextColor(Color.WHITE);
			}
		} else {
			mMgrBatActivity.setImg(mImgLevel, curLevel);
			mImgSlow.setImageResource(R.drawable.battery_bulb_deactive);
			mTxtSlow.setText(getResources()
					.getString(R.string.keep_charge_wait));
			mTxtSlow.setTextColor(Color.BLACK);
			mImgQuick.setImageResource(R.drawable.battery_bulb_deactive);
			mTxtQuick.setText(getResources().getString(
					R.string.quick_charge_wait));
			mTxtQuick.setTextColor(Color.BLACK);
			mImgRec.setImageResource(R.drawable.battery_bulb_deactive);
			mTxtRec.setText(getResources().getString(
					R.string.recycle_charge_wait));
			mTxtRec.setTextColor(Color.BLACK);
		}
	}
}
