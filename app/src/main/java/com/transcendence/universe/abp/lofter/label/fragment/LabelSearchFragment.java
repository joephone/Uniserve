package com.transcendence.universe.abp.lofter.label.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.universe.R;
import com.transcendence.universe.adapter.TabLayoutPagerAdapter;
import com.transcendence.universe.abp.main.fragments.BaseFragment;
import com.transcendence.universe.utils.Logs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joephone on 2019/2/28 17:37
 * E-Mail Address：joephonechen@gmail.com
 */

public class LabelSearchFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayoutPagerAdapter mAdapter;
    //viewpager数据    fragment列表  标题列表
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    //标记当前页卡位置，默认为第一个页卡
    private int mPosition = 0;
    private View rootView;  //缓存Fragment view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logs.logE("LabelSearchFragment onCreateView");
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_label_search, container, false);
            init(rootView);
        }else {  //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if(parent!=null){
                parent.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logs.logE("LabelSearchFragment onViewCreated");
    }

    private void init(View rootView) {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        initList();
        initTabLayout();
    }


    private void initTabLayout() {
        /**
         * 注册广播
         */
        mAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 在页面消失的时候反注册广播,防止内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
//        mContext.unregisterReceiver(rdb);
    }

    private void initList() {
        Logs.logE("LabelSearchFragment initList");
        mTitleList.add("标签");
        mTitleList.add("用户");
        mTitleList.add("专题");
        mFragmentList.add(new LabelSearchTagDetailFragment());
        mFragmentList.add(new LabelSearchTagDetailFragment());
        mFragmentList.add(new LabelSearchTagDetailFragment());
    }

    public static LabelSearchFragment newInstance(String param1) {
        LabelSearchFragment fragment = new LabelSearchFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
