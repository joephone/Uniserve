package com.transcendence.universe.abp.base.act;

import android.os.Bundle;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.BaseActivity;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.Global;
import com.transcendence.universe.utils.Loger;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by joephone on 2017/6/12.
 */

public class LifeCycleActivity extends TitleBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_cycle);

        Loger.i(Global.TAG,"onCreate");
        //1 启动Activity：系统会先调用onCreate方法，然后调用onStart方法，最后调用onResume，Activity进入运行状态。

        //7.用户退出当前Activity：系统先调用onPause方法，然后调用onStop方法，最后调用onDestory方法，结束当前Activity。
    }

    @Override
    protected void onStart() {
        super.onStart();
        Loger.i(Global.TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Loger.i(Global.TAG,"onResume");
        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Loger.i(Global.TAG,"onPause");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Loger.i(Global.TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Loger.i(Global.TAG,"onDestroy");
    }


}
