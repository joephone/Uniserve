package com.transcendence.universe.abp.ui.customview.flowlayout.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.EventTestFragment;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.GravityFragment;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.LimitSelectedFragment;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.ListViewTestFragment;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.MulSeletedFragment;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.ScrollViewTestFragment;
import com.transcendence.universe.abp.ui.customview.flowlayout.fragment.SingleChooseFragment;
import com.umeng.analytics.MobclickAgent;
import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/17 15:01
 * E-Mail Address：joephonechen@gmail.com
 */

public class SubTagActivity extends TitleBarActivity {

    private ViewPager vp;
    private TabLayout mTabLayout;

    private String[] mTabTitles = new String[]
            {"Muli Selected", "Limit 3",
                    "Event Test", "ScrollView Test", "Single Choose", "Gravity", "ListView Sample"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_tag_main_activity);
        ButterKnife.bind(this);
        setTitle("");
        init();
    }

    protected void init() {
        mTabLayout = findViewById(R.id.id_tablayout);
        vp = findViewById(R.id.viewPager);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new MulSeletedFragment();
                    case 1:
                        return new LimitSelectedFragment();
                    case 2:
                        return new EventTestFragment();
                    case 3:
                        return new ScrollViewTestFragment();
                    case 4:
                        return new SingleChooseFragment();
                    case 5:
                        return new GravityFragment();
                    case 6:
                        return new ListViewTestFragment();
                    default:
                        return new MulSeletedFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return mTabTitles[position];
            }

            @Override
            public int getCount() {
                return mTabTitles.length;
            }
        });

        mTabLayout.setupWithViewPager(vp);
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
