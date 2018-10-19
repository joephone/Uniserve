package com.transcendence.universe.abp.base.battery.act;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.transcendence.universe.R;


/**
 * Created by Joephone on 2018/10/19 17:07
 * E-Mail Address：joephonechen@gmail.com
 */

public class BatteryActivity extends TabActivity {
	TextView txtTextView;
	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tabHost = getTabHost();
		tabHost.setup();

//		tabHost.setBackgroundResource(R.drawable.background);

		View view = LayoutInflater.from(this).inflate(
				R.layout.item_tab_battery, null);
		view.setBackgroundResource(R.drawable.battery_backimg_fouce);
		txtTextView = (TextView) view.findViewById(R.id.tab_txt);
		Drawable drawable = getResources().getDrawable(
				R.drawable.battery_manager);
		txtTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawable,
				null, null);
		txtTextView.setText("电池监控");
		tabHost.addTab(tabHost.newTabSpec("battery_manager").setIndicator(view)
				.setContent(new Intent(this, BatteryManagerActivity.class)));

		View view1 = LayoutInflater.from(this).inflate(
				R.layout.item_tab_battery, null);
		view1.setBackgroundResource(R.drawable.battery_backimg_fouce);
		txtTextView = (TextView) view1.findViewById(R.id.tab_txt);
		Drawable drawable1 = getResources().getDrawable(R.drawable.battery_fix);
		txtTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawable1,
				null, null);
		txtTextView.setText("电池维护");
		tabHost.addTab(tabHost.newTabSpec("battery_fix").setIndicator(view1)
				.setContent(new Intent(this, BatteryFixActivity.class)));

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				changeTab();// 换背景
			}
		});
		// 设置默认背景
		tabHost.setCurrentTab(0);
		changeTab();
	}

	public void changeTab() {
		int current = tabHost.getCurrentTab();
		int count = getTabWidget().getChildCount();

		for (int i = 0; i < count; i++) {
			View view = getTabWidget().getChildTabViewAt(i);
			if (current == i) {
				// 把自己换成选中状态
				view.setBackgroundResource(R.drawable.battery_img_backgra);
			} else {
				// 把别人换成未选中状态,透明色
				view.setBackgroundResource(android.R.color.transparent);
			}
		}
	}
}

