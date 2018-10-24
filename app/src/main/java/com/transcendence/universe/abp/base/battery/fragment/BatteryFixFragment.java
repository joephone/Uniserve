/**
 * 
 */
package com.transcendence.universe.abp.base.battery.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.fragments.BaseFragment;
import com.transcendence.universe.utils.SharedPreferenceUtil;


public class BatteryFixFragment extends BaseFragment {
//	private BatteryManagerActivity mMgrBatActivity;
	private SharedPreferenceUtil preferencesUtil;
	private TextView mTxtHealth, mTxtStatus, mTxtLevel, mTxtQuick, mTxtRec,
			mTxtSlow;
	private ImageView mImgLevel, mImgQuick, mImgRec, mImgSlow;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_battery_fix, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		init(view);
		getInfo();
	}


	/**
	 * 初始化控件
	 */
	public void init(View view) {
//		mMgrBatActivity = new BatteryManagerActivity();
		preferencesUtil = new SharedPreferenceUtil(getActivity());

		mTxtHealth = (TextView) view.findViewById(R.id.bat_health);
		mTxtStatus = (TextView) view.findViewById(R.id.bat_level_sta);
		mTxtLevel = (TextView) view.findViewById(R.id.bat_level);
		mTxtQuick = (TextView) view.findViewById(R.id.txt_quick_charge);
		mTxtRec = (TextView) view.findViewById(R.id.txt_recycle_charge);
		mTxtSlow = (TextView) view.findViewById(R.id.txt_keep_charge);
		mImgLevel = (ImageView) view.findViewById(R.id.bat_sta_img);
		mImgQuick = (ImageView) view.findViewById(R.id.img_quick_charge);
		mImgRec = (ImageView) view.findViewById(R.id.img_recycle_charge);
		mImgSlow = (ImageView) view.findViewById(R.id.img_keep_charge);
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
//			mMgrBatActivity.setImg(mImgLevel, curLevel);
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
