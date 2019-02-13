package com.transcendence.universe.abp.index.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.act.MarqueeLightActivity;
import com.transcendence.universe.abp.classic.luckyturntable.act.MarqueeLightFive;
import com.transcendence.universe.abp.classic.luckyturntable.act.MarqueeLightFourActivity;
import com.transcendence.universe.abp.classic.luckyturntable.act.MarqueeLightOneActivity;
import com.transcendence.universe.abp.classic.luckyturntable.act.MarqueeLightThreeActivity;
import com.transcendence.universe.abp.classic.luckyturntable.act.MarqueeLightTwoActivity;
import com.transcendence.universe.abp.classic.luckyturntable.act.TigerMachineActivity;
import com.transcendence.universe.abp.index.adapter.IndexAdapter;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.abp.ui.layout.relative.act.LuckyPanActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/18 17:50
 * E-Mail Address：joephonechen@gmail.com
 */

public class LuckyIndexAct extends TitleBarActivity implements IndexAdapter.IndexEvent {

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

        sourceList.add("LuckyPan");
        sourceList.add("跑马灯抽奖方案一");
        sourceList.add("跑马灯抽奖方案二");
        sourceList.add("跑马灯抽奖方案三");
        sourceList.add("跑马灯抽奖方案经典版");
        sourceList.add("跑马灯抽奖方案4");
        sourceList.add("跑马灯抽奖方案5");
        sourceList.add("老虎机");
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
                intent = new Intent(mActivity,LuckyPanActivity.class);
                break;
            case 1:
                intent = new Intent(mActivity,MarqueeLightOneActivity.class);
                break;
            case 2:
                intent = new Intent(mActivity,MarqueeLightTwoActivity.class);
                break;
            case 3:
                intent = new Intent(mActivity,MarqueeLightThreeActivity.class);     //MarqueeLightThreeActivity
                break;
            case 4:
                intent = new Intent(mActivity,MarqueeLightActivity.class);
                break;
            case 5:
                intent = new Intent(mActivity,MarqueeLightFourActivity.class);
                break;
            case 6:
                intent = new Intent(mActivity,MarqueeLightFive.class);
                break;
            case 7:
                intent = new Intent(mActivity,TigerMachineActivity.class);
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
