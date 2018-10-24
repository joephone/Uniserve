package com.transcendence.universe.abp.base.battery.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.transcendence.universe.R;
import com.transcendence.universe.abp.base.battery.fragment.BatteryFixFragment;
import com.transcendence.universe.abp.base.battery.fragment.BatteryManagerFragment;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.adapter.MyPageAdapter;
import com.transcendence.universe.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joephone on 2018/10/19 17:07
 * E-Mail Address：joephonechen@gmail.com
 */

public class BatteryActivity extends TitleBarActivity {

    TabLayout tabLayout;
    ViewPager vp;
    private MyPageAdapter mPageAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_main);
        setTitle("电话咨询");
        init();
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.viewPager);
        mPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        titles.add("待回复");
        titles.add("已完成");
        titles.add("已失效");
        BatteryManagerFragment managerFragment = new BatteryManagerFragment();
        BatteryFixFragment fixFragment = new BatteryFixFragment();
        fragments.add(managerFragment);
        fragments.add(fixFragment);

        mPageAdapter.setTitles(titles);
        mPageAdapter.setFragments(fragments);
        vp.setOffscreenPageLimit(0);
        vp.setAdapter(mPageAdapter);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setTabsFromPagerAdapter(mPageAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                AppUtils.setIndicator(tabLayout, 40, 40);
            }
        });


    }

//	TextView txtTextView;
//	TabHost tabHost;

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		tabHost = getTabHost();
//		tabHost.setup();
//
////		tabHost.setBackgroundResource(R.drawable.background);
//
//		View view = LayoutInflater.from(this).inflate(
//				R.layout.item_tab_battery, null);
//		view.setBackgroundResource(R.drawable.battery_backimg_fouce);
//		txtTextView = (TextView) view.findViewById(R.id.tab_txt);
//		Drawable drawable = getResources().getDrawable(
//				R.drawable.battery_manager);
//		txtTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawable,
//				null, null);
//		txtTextView.setText("电池监控");
//		tabHost.addTab(tabHost.newTabSpec("battery_manager").setIndicator(view)
//				.setContent(new Intent(this, BatteryManagerActivity.class)));
//
//		View view1 = LayoutInflater.from(this).inflate(
//				R.layout.item_tab_battery, null);
//		view1.setBackgroundResource(R.drawable.battery_backimg_fouce);
//		txtTextView = (TextView) view1.findViewById(R.id.tab_txt);
//		Drawable drawable1 = getResources().getDrawable(R.drawable.battery_fix);
//		txtTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawable1,
//				null, null);
//		txtTextView.setText("电池维护");
//		tabHost.addTab(tabHost.newTabSpec("battery_fix").setIndicator(view1)
//				.setContent(new Intent(this, BatteryFixActivity.class)));
//
//		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
//
//			@Override
//			public void onTabChanged(String tabId) {
//				changeTab();// 换背景
//			}
//		});
//		// 设置默认背景
//		tabHost.setCurrentTab(0);
//		changeTab();
//	}
//
//	public void changeTab() {
//		int current = tabHost.getCurrentTab();
//		int count = getTabWidget().getChildCount();
//
//		for (int i = 0; i < count; i++) {
//			View view = getTabWidget().getChildTabViewAt(i);
//			if (current == i) {
//				// 把自己换成选中状态
//				view.setBackgroundResource(R.drawable.battery_img_backgra);
//			} else {
//				// 把别人换成未选中状态,透明色
//				view.setBackgroundResource(android.R.color.transparent);
//			}
//		}
//	}
}

