package com.transcendence.universe.abp.lofter.label.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.fragments.BaseFragment;
import com.transcendence.universe.utils.Logs;

/**
 * Created by Joephone on 2019/3/1 14:10
 * E-Mail Address：joephonechen@gmail.com
 */

public class LabelMainFragment extends BaseFragment {

    private View rootView;  //缓存Fragment view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logs.logE("LabelMainFragment onCreateView");
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_label_main, container, false);
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
//        replaceFragment(new LabelMainFragment(), R.id.fl_label_fg);
    }



    /**
     * 在页面消失的时候反注册广播,防止内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
//        mContext.unregisterReceiver(rdb);
    }

    /**
     * @param fg 需要替换的碎片
     * @param id 需要替换的位置
     */
    private void replaceFragment(Fragment fg, int id) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fg);
        ft.commit();
    }

    public static LabelMainFragment newInstance(String param1) {
        LabelMainFragment fragment = new LabelMainFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
