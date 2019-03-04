package com.transcendence.universe.abp.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/24 11:10
 * E-Mail Address：joephonechen@gmail.com
 */

public class BaseFragment extends Fragment {

    private static final String ARG_SHOW_TEXT = "text";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    public static BaseFragment newInstance(String param1) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
