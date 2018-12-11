package com.transcendence.universe.abp.base.battery.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.abp.main.fragments.BaseFragment;
import com.transcendence.universe.utils.SharedPreferenceUtil;

public class BatteryManagerFragment extends BaseFragment {
	/** 图片变化延迟时间 */
	private static final long DELAT_TIME = 1000;
	/** 电池状况显示控件 */
	private TextView mBatCurTxt, mBatStaTxt, mBatTemperature, mBatVolTxt,
			mBatUseTxt;
	/** 广播接收器 */
	private BatReceiver receiver;
	/** 电池当前电量 */
	private int mCurLevel;
	/** 电池电量显示图片 */
	private ImageView mBatImg;
	/** 电量显示图片的下标 */
	int mIndex;
	/** 电池状态 */
	int statusIndex;
	/** 定义方法类对象 */
	SharedPreferenceUtil preferencesUtil;
	/** 根据电池电量显示的相应的图片 */
	int[] mBatImgDra = { R.drawable.bt0, R.drawable.bt10, R.drawable.bt20,
			R.drawable.bt30, R.drawable.bt50, R.drawable.bt70, R.drawable.bt80,
			R.drawable.bt90, R.drawable.bt100 };


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_battery_manager, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		/** 初始化方法类对象 */
		preferencesUtil = new SharedPreferenceUtil(getActivity());
		/** 初始化控件 */
		init(view);
	}


	/** 处理信息，控制充电时图片变化 */
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			int what = msg.what;
			mBatImg.setImageResource(mBatImgDra[what]);
		}
	};

	/**
	 * 初始化控件
	 */
	public void init(View view) {
		mBatImg = (ImageView) view.findViewById(R.id.bat_img);
		mBatCurTxt = (TextView) view.findViewById(R.id.bat_cur);
		mBatStaTxt = (TextView) view.findViewById(R.id.bat_state);
		mBatTemperature = (TextView) view.findViewById(R.id.bat_temperature);
		mBatVolTxt = (TextView) view.findViewById(R.id.bat_v);
		mBatUseTxt = (TextView) view.findViewById(R.id.bat_use_state);
	}

	/**
	 * 注册广播接收器
	 */
	@Override
	public void onResume() {
		super.onResume();
		receiver = new BatReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		getActivity().registerReceiver(receiver, filter);
	}

	/**
	 * 注销广播接收器
	 */
	@Override
	public void onDestroy() {
		getActivity().unregisterReceiver(receiver);
		super.onDestroy();
	}

	/**
	 * 广播接收器类
	 */
	class BatReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			/** 获取电池最大电量 */
			int maxLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			mCurLevel = level * 100 / maxLevel;
			if(mCurLevel>15){
				mBatCurTxt.setText(mCurLevel + "%");
			}else{
				mBatCurTxt.setText(mCurLevel + "%\n请及时充电");
				mBatCurTxt.setTextColor(Color.RED);
			}

			/** 获取电池状态信息 */
			statusIndex = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
			String[] status = getResources().getStringArray(
					R.array.battery_status);
			mBatStaTxt.setText(status[statusIndex]);

			/** 获取电池电压 */
			int vol = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
			mBatVolTxt.setText(vol + "mV");

			/** 获取电池使用状况 */
			int healthIndex = intent
					.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
			String[] health = getResources().getStringArray(
					R.array.battery_health);
			mBatUseTxt.setText(health[healthIndex]);

			/** 获取电池温度 */
			int temperature = intent.getIntExtra(
					BatteryManager.EXTRA_TEMPERATURE, 0);
			if ((temperature / 10) < 40) {
				mBatTemperature.setText(temperature / 10 + "摄氏度");
			} else {
				mBatTemperature.setTextColor(Color.RED);
				mBatTemperature.setText(" " + temperature / 10 + "摄氏度\n！温度过高");
			}

			/** 调用当前需要显示的图片方法 */
			setImg(mBatImg, mCurLevel);

			/** 存储当前的电量 */
			preferencesUtil.save("curLecel", mCurLevel);
			/** 存储电池的状态 */
			preferencesUtil.save("status", status[statusIndex]);
			/** 存储电池的使用状况 */
			preferencesUtil.save("health", healthIndex);

			// 启动子线程，充电状态时，电量的图片进行切换
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (statusIndex == 2) {
						Message msg = mHandler.obtainMessage();
						msg.what = mIndex;
						mHandler.sendMessage(msg);
						try {
							Thread.sleep(DELAT_TIME);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mIndex++;
						if (mIndex > 8) {
							mIndex = 0;
						}
					}
				}
			}).start();
		}
	}

	/**
	 * 设置当前需要显示的图片
	 */
	public void setImg(ImageView batImg, int current) {
		this.mCurLevel = current;
		this.mBatImg = batImg;
		if ((current < 10) && (current >= 0)) {
			mBatImg.setImageResource(mBatImgDra[0]);
		} else if ((current < 20) && (current >= 10)) {
			mBatImg.setImageResource(mBatImgDra[1]);
		} else if ((current < 30) && (current >= 20)) {
			mBatImg.setImageResource(mBatImgDra[2]);
		} else if ((current < 40) && (current >= 30)) {
			mBatImg.setImageResource(mBatImgDra[3]);
		} else if ((current < 50) && (current >= 40)) {
			mBatImg.setImageResource(mBatImgDra[4]);
		} else if ((current < 70) && (current >= 50)) {
			mBatImg.setImageResource(mBatImgDra[5]);
		} else if ((current < 80) && (current >= 70)) {
			mBatImg.setImageResource(mBatImgDra[6]);
		} else if ((current < 90) && (current >= 80)) {
			mBatImg.setImageResource(mBatImgDra[7]);
		} else if (current >= 90) {
			mBatImg.setImageResource(mBatImgDra[8]);
		}
	}
}
