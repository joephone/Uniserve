package com.transcendence.universe.abp.base.act;

import android.app.Activity;
import android.os.Bundle;

import com.transcendence.universe.R;
import com.transcendence.universe.util.Loger;

/**
 * Created by joephone on 2017/6/12.
 */
public class LifeCycleAct extends Activity {

    private String tag = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_cycle);

        Loger.i(tag,"onCreate");
        //1 启动Activity：系统会先调用onCreate方法，然后调用onStart方法，最后调用onResume，Activity进入运行状态。

        //7.用户退出当前Activity：系统先调用onPause方法，然后调用onStop方法，最后调用onDestory方法，结束当前Activity。
    }

    @Override
    protected void onStart() {
        super.onStart();
        Loger.i(tag,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Loger.i(tag,"onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Loger.i(tag,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Loger.i(tag,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Loger.i(tag,"onDestroy");
    }
}
