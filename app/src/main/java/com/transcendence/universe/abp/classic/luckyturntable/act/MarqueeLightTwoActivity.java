package com.transcendence.universe.abp.classic.luckyturntable.act;
import android.os.Bundle;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewTwo;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.Logs;

/**
 * Created by Joephone on 2018/12/18 12:45
 * E-Mail Address：joephonechen@gmail.com
 */

public class MarqueeLightTwoActivity extends TitleBarActivity {
    private NineGridViewTwo mLotteryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nine_grid_lucky2);
        setTitle("抽奖活动", R.mipmap.icon_share);

        mLotteryView = (NineGridViewTwo) findViewById(R.id.lotteryView);
        mLotteryView.setOnStartListener(new NineGridViewTwo.OnStartListener() {
            @Override
            public void onStart() {
                Logs.logI( "onStart");
                mLotteryView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLotteryView.stop(5);
                    }
                }, 3000L);
            }
        });
    }

}
