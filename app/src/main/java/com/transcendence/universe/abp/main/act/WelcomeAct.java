package com.transcendence.universe.abp.main.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.index.act.IndexAct;
import com.transcendence.universe.utils.Global;
import com.umeng.analytics.MobclickAgent;

public class WelcomeAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeAct.this,IndexAct.class);
                startActivity(intent);
                finish();
            }
        }, Global.WELCOME_TIME);
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
