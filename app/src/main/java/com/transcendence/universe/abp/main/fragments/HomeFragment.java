package com.transcendence.universe.abp.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.adapter.MyFragmentPagerAdapter;
import com.transcendence.universe.utils.Logs;
import com.transcendence.universe.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joephone on 2019/2/27 15:52
 * E-Mail Address：joephonechen@gmail.com
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tagSelect)
    ImageView tagSelect;
//    @Bind(R.id.pageIndicator)
//    TabPageIndicator pageIndicator;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;
    private int index = 0; //tab 索引

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logs.logE("onCreateView");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {
        mFragments = new ArrayList<>();
        mFragments.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_one)));
        mFragments.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_two)));
        mFragments.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_three)));
        mFragments.add(BlankFragment.newInstance(StringUtils.getString(R.string.home_four)));
        // init view pager
        mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tagSelect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tagSelect:
                break;
        }
    }


    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
