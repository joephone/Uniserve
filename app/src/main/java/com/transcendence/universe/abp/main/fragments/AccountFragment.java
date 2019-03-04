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
 * Created by Joephone on 2019/3/1 17:52
 * E-Mail Address：joephonechen@gmail.com
 */

public class AccountFragment extends BaseFragment {


//    @Bind(R.id.pageIndicator)
//    TabPageIndicator pageIndicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logs.logE("onCreateView");
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
