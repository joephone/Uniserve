package com.transcendence.universe.abpPublicModule.location.act;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.lofter.label.fragment.LabelSearchTagDetailFragment;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.abp.main.fragments.BlankFragment;
import com.transcendence.universe.adapter.TabLayoutPagerAdapter;
import com.transcendence.universe.utils.Logs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joephone on 2019/3/4 15:37
 * E-Mail Address：joephonechen@gmail.com
 */

public class LocationActivity extends TitleBarActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayoutPagerAdapter mAdapter;
    //viewpager数据    fragment列表  标题列表
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    //标记当前页卡位置，默认为第一个页卡
    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initViews();
        initList();
        initTabs();
    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initList() {
        mTitleList.add("国内");
        mTitleList.add("海外");
        mFragmentList.add(new BlankFragment());
        mFragmentList.add(new BlankFragment());
    }

    private void initTabs() {
        mAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
    }
}
