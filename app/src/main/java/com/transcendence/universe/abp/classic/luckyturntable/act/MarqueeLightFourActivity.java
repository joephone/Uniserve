package com.transcendence.universe.abp.classic.luckyturntable.act;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.view.ItemView;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewThreeItem;
import com.transcendence.universe.abp.main.act.TitleBarActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MarqueeLightFourActivity extends TitleBarActivity {


//    private TextView tv1;
//    private TextView tv2;
//    private TextView tv3;
//    private TextView tv4;
//    private TextView tvStart;
//    private TextView tv5;
//    private TextView tv6;
//    private TextView tv7;
//    private TextView tv8;
//    private TextView tvNotice;

    private NineGridViewThreeItem itemView1, itemView2, itemView3,
            itemView4, itemView6,
            itemView7, itemView8, itemView9;

    private ItemView[] itemViewArr = new ItemView[8];

    private List<TextView> views = new LinkedList<>();//所有的视图
    private int timeC= 100;//变色时间间隔
    private int lightPosition = 0;//当前亮灯位置,从0开始
    private int runCount = 10;//需要转多少圈
    private int lunckyPosition = 4;//中奖的幸运位置,从0开始

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nine_grid_lucky4);
        init();
    }

    public void init() {
//        tv1 = (TextView) findViewById(R.id.tv1);
//        tv2 = (TextView) findViewById(R.id.tv2);
//        tv3 = (TextView) findViewById(R.id.tv3);
//        tv4 = (TextView) findViewById(R.id.tv4);
//        tv5 = (TextView) findViewById(R.id.tv5);
//        tv6 = (TextView) findViewById(R.id.tv6);
//        tv7 = (TextView) findViewById(R.id.tv7);
//        tv8 = (TextView) findViewById(R.id.tv8);
//        tvStart = (TextView) findViewById(R.id.tvStart);
//        tvNotice = (TextView) findViewById(R.id.tv_notice);
//        views.add(tv1);
//        views.add(tv2);
//        views.add(tv3);
//        views.add(tv4);
//        views.add(tv5);
//        views.add(tv6);
//        views.add(tv7);
//        views.add(tv8);

        itemView1 = (NineGridViewThreeItem) findViewById(R.id.item1);
        itemView2 = (NineGridViewThreeItem) findViewById(R.id.item2);
        itemView3 = (NineGridViewThreeItem) findViewById(R.id.item3);
        itemView4 = (NineGridViewThreeItem) findViewById(R.id.item4);
        itemView6 = (NineGridViewThreeItem) findViewById(R.id.item6);
        itemView7 = (NineGridViewThreeItem) findViewById(R.id.item7);
        itemView8 = (NineGridViewThreeItem) findViewById(R.id.item8);
        itemView9 = (NineGridViewThreeItem) findViewById(R.id.item9);

//        try {
//            tvStart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    tvStart.setClickable(false);
//                    tvStart.setEnabled(false);
//                    tvNotice.setText("");
//                    runCount = 10;
//                    timeC = 100;
//                    views.get(lunckyPosition).setBackgroundColor(Color.TRANSPARENT);
//                    lunckyPosition = randomNum(0,7);
//                    new TimeCount(timeC*9,timeC).start();
//
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 生成随机数
     * @param minNum
     * @param maxNum
     * @return
     */
    private int randomNum(int minNum,int maxNum) {
        int max = maxNum;
        int min = minNum;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

    class TimeCount extends CountDownTimer{
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            lightPosition = 0;
        }

        @Override
        public void onTick(long millisUntilFinished) {

            Log.i(">>>","---"+lightPosition);
            //如果是最后一次滚动
            if (runCount>0){
                if (lightPosition>0){
                    views.get(lightPosition-1).setBackgroundColor(Color.TRANSPARENT);
                }
                if (lightPosition<8){
                    views.get(lightPosition).setBackgroundColor(Color.RED);
                }

            }else if (runCount==0){

                if (lightPosition<=lunckyPosition){
                    if (lightPosition>0){
                        views.get(lightPosition-1).setBackgroundColor(Color.TRANSPARENT);
                    }
                    if (lightPosition<8){
                        views.get(lightPosition).setBackgroundColor(Color.RED);
                    }
                }
            }

            lightPosition++;
        }
        @Override
        public void onFinish() {
            Log.i(">>>","onFinish=="+runCount);
            //如果不是最后一圈，需要还原最后一块的颜色
            TextView tvLast= views.get(7);
            if (runCount!=0){
                tvLast.setBackgroundColor(Color.TRANSPARENT);
                //最后几转速度变慢
                if (runCount<3) timeC += 200;
                new TimeCount(timeC*9,timeC).start();
                runCount--;
            }
            //如果是最后一圈且计时也已经结束
            if (runCount==0&&lightPosition==8){
//                tvStart.setClickable(true);
//                tvStart.setEnabled(true);
//                tvNotice.setText("恭喜你抽中: "+views.get(lunckyPosition).getText().toString());
                if (lunckyPosition!=views.size())
                    tvLast.setBackgroundColor(Color.TRANSPARENT);

            }

        }
    }


}
