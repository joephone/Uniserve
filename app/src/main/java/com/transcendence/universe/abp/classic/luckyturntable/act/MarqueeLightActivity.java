package com.transcendence.universe.abp.classic.luckyturntable.act;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewClassic;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewThree;
import com.transcendence.universe.abp.main.act.TitleBarActivity;

import java.util.Random;

public class MarqueeLightActivity extends TitleBarActivity {


    private NineGridViewClassic luckyPanelView;
    private Button btn_action;
    private long drawTime; //抽奖时间
    private int MARK_LUCKY = 6; //中奖标记
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nine_grid_lucky_classic);

        luckyPanelView = (NineGridViewClassic) findViewById(R.id.lucky_panel);
        btn_action = (Button) findViewById(R.id.btn_action);

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MARK_LUCKY = new Random().nextInt(8);
                Log.e("LuckyMonkeyPanelView", "====stay===" + MARK_LUCKY);
                //开始抽奖
                if (!luckyPanelView.isGameRunning()) {
                    drawTime = System.currentTimeMillis();
                    luckyPanelView.startGame();
                    getLuck();
                }
            }
        });
    }

    private void getLuck() {
        long delay = 0; //延长时间
        long duration = System.currentTimeMillis() - drawTime;
        if (duration < 5000) {
            delay = 5000 - duration;
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (MarqueeLightActivity.this.isFinishing()) {
                    return;
                }

                luckyPanelView.tryToStop(MARK_LUCKY);
//                luckyPanelView.setGameListener(new LuckyMonkeyPanelView.LuckyMonkeyAnimationListener() {
//                    @Override
//                    public void onAnimationEnd() {
//                        //延长1S弹出抽奖结果
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
////                                Toast.makeText(SudokuTurnTableActivity.this, MARK_LUCKY, Toast.LENGTH_SHORT).show();
//                            }
//                        }, 1000);
//                    }
//                });
            }
        }, delay);
    }


}
