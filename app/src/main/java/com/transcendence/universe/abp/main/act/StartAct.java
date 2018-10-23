package com.transcendence.universe.abp.main.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.transcendence.universe.R;
import com.transcendence.universe.abp.index.act.IndexAct;
import com.transcendence.universe.abpPublicModule.imagesfresco.DisplayImage;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/5/11.
 */
public class StartAct extends Activity {

    @Bind(R.id.ivStart)
    SimpleDraweeView ivStart;

    TimeCount timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.start);
        ButterKnife.bind(this);

        timer = new TimeCount(2500,1000);
        timer.start();

        DisplayImage.getInstance().displayAssetImage(ivStart, "universe.gif");
    }



    class TimeCount extends CountDownTimer {


        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(StartAct.this,IndexAct.class);
            startActivity(intent);
            finish();
        }
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
