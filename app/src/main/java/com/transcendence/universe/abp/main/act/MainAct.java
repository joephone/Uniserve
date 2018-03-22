package com.transcendence.universe.abp.main.act;

import android.app.Activity;
import android.os.Bundle;

import com.transcendence.universe.R;
import com.umeng.analytics.MobclickAgent;

public class MainAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
