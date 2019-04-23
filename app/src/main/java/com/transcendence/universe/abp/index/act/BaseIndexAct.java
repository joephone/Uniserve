package com.transcendence.universe.abp.index.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.base.act.AndroidInfoActivity;
import com.transcendence.universe.abp.base.act.IDInfoActivity;
import com.transcendence.universe.abp.base.act.LifeCycleActivity;
import com.transcendence.universe.abp.base.act.ScreenKeyDownActivity;
import com.transcendence.universe.abp.base.act.SoundPlayActivity;
import com.transcendence.universe.abp.base.act.TelInfoActivity;
import com.transcendence.universe.abp.base.battery.act.BatteryActivity;
import com.transcendence.universe.abp.index.adapter.IndexAdapter;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.Logs;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/5/12.
 */
public class BaseIndexAct extends TitleBarActivity implements IndexAdapter.IndexEvent {

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private String tag = this.getClass().getName();
    private Activity mActivity;
    private IndexAdapter adapter;
    private List<String> sourceList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        ButterKnife.bind(this);
        init();

    }

    @Override
    public void init() {
        sourceList.add("生命周期");
        sourceList.add("电池");
        sourceList.add("手机品牌、商家、版本号等信息");
        sourceList.add("电话工具类，手机号、运营商、IMEI、IMSI等信息");
        sourceList.add("Android 回退键监听");
        sourceList.add("beeb播放");
        sourceList.add("UUID");
        mActivity = this;

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new IndexAdapter(this,sourceList);
        mRecyclerView.setAdapter(adapter);
        adapter.setEvent(this);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent = new Intent(mActivity,LifeCycleActivity.class);
                break;
            case 1:
                intent = new Intent(mActivity,BatteryActivity.class);
                break;
            case 2:
                intent = new Intent(mActivity,AndroidInfoActivity.class);
                break;
            case 3:
                intent = new Intent(mActivity,TelInfoActivity.class);
                break;
            case 4:
                intent = new Intent(mActivity,ScreenKeyDownActivity.class);
                break;
            case 5:
                intent = new Intent(mActivity,SoundPlayActivity.class);
                break;
            case 6:
                intent = new Intent(mActivity,IDInfoActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
