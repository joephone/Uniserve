package com.transcendence.universe.abp.index.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.fringerprint.act.FingerprintMainActivity;
import com.transcendence.universe.abp.classic.hongyang.act.HongyangMainAct;
import com.transcendence.universe.abp.classic.weiganloopview.act.WeiganMainAct;
import com.transcendence.universe.abp.index.adapter.IndexAdapter;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/5/12.
 */
public class ClassicIndexAct extends TitleBarActivity implements IndexAdapter.IndexEvent {

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

        sourceList.add("鸿洋自定义View");
        sourceList.add("伟根loopView");
        sourceList.add("幸运抽奖");
        sourceList.add("指纹");

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
                intent = new Intent(mActivity,HongyangMainAct.class);
                break;
            case 1:
                intent = new Intent(mActivity,WeiganMainAct.class);
                break;
            case 2:
                intent = new Intent(mActivity,LuckyIndexAct.class);
                break;
            case 3:
                intent = new Intent(mActivity,FingerprintMainActivity.class);
                break;
            case 4:

                break;
            case 5:
                break;
            case 6:
                break;
        }
        startActivity(intent);
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
