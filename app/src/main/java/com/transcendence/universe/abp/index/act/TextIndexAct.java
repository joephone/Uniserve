package com.transcendence.universe.abp.index.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.index.adapter.IndexAdapter;
import com.transcendence.universe.abp.ui.widget.textview.act.CareerShowMoreAct;
import com.transcendence.universe.abp.ui.widget.textview.act.RippleEffectAct;
import com.transcendence.universe.abp.ui.widget.textview.httptextview.act.HttpTextAct;
import com.transcendence.universe.utils.Loger;
import com.transcendence.universe.utils.StringUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/5/12.
 */
public class TextIndexAct extends Activity implements IndexAdapter.IndexEvent {

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

        sourceList.add(StringUtils.getString(R.string.rippleEffect));
        sourceList.add("自动识别TextView中的链接,并点击打开链接");
        sourceList.add("ShowMoreText");
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
        Loger.i(tag,"onItemClick");
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent = new Intent(mActivity,RippleEffectAct.class);
                break;
            case 1:
                intent = new Intent(mActivity,HttpTextAct.class);
                break;
            case 2:
                intent = new Intent(mActivity,CareerShowMoreAct.class);
                break;
            case 3:
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
