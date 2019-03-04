package com.transcendence.universe.abp.main.act;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.lofter.label.fragment.LabelFragment;
import com.transcendence.universe.abp.main.adapter.MyFragmentPagerAdapter;
import com.transcendence.universe.abp.main.fragments.AccountFragment;
import com.transcendence.universe.abp.main.fragments.BlankFragment;
import com.transcendence.universe.abp.main.fragments.HomeFragment;
import com.transcendence.universe.abp.main.util.PopupMenuUtil;
import com.transcendence.universe.utils.StringUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * 超验主义（transcendentalism）的核心观点是主张人能超越感觉和理性而直接认识真理，强调直觉的重要性，
 * 其认为人类世界的一切都是宇宙的一个缩影--"世界将其自身缩小成为一滴露水"（爱默生语）
 */

public class MainAct extends TitleBarActivity {
    private ImageView ivAdd;
    private ImageView back;
    private RelativeLayout layoutAdd;
    private Context mContext;

    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;
    private int index = 0; //tab 索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();
        initTabs();
    }

    private void initViews() {
        setTitle("");
        mContext = this;
        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        back = (ImageView) findViewById(R.id.back);
        back.setVisibility(View.GONE);
        layoutAdd = (RelativeLayout) findViewById(R.id.layoutAdd);
        layoutAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuUtil.getInstance().show(mContext, ivAdd);
            }
        });
    }

    private void initTabs() {
        // find view
        mViewPager = findViewById(R.id.vp);
        mTabRadioGroup = findViewById(R.id.tabs);
//        mTabRadioGroup.removeViewAt(2);
        // init fragment

        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance(StringUtils.getString(R.string.tab_one)));
        mFragments.add(LabelFragment.newInstance(StringUtils.getString(R.string.tab_two)));
        mFragments.add(BlankFragment.newInstance(StringUtils.getString(R.string.tab_three)));
        mFragments.add(new AccountFragment());
        // init view pager
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.i(LOG_TAG, "onKeyDown: keyCode -- " + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_BACK");
//                break;
                return false;//拦截事件
            case KeyEvent.KEYCODE_MENU:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_MENU");
                break;
            case KeyEvent.KEYCODE_HOME:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_HOME");
                // 收不到
                break;
            case KeyEvent.KEYCODE_APP_SWITCH:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }




    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            index = position;
//            if(index !=2){
                RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(index);
                radioButton.setChecked(true);
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
//                if(i !=2){
                    if (group.getChildAt(i).getId() == checkedId) {
                        mViewPager.setCurrentItem(i);
                        return;
//                    }
                }

            }
        }
    };


}
