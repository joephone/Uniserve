package com.transcendence.universe.abp.main.act;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.advancedpagerslidingtabstrip.WeiboTabActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建日期：2017/12/8  10:04
 * 描述:  启动页
 * 作者:joephone
 */

public class SplashAct extends BaseActivity {

    @Bind(R.id.ivStart)
    ImageView ivStart;
    private final String TAG = this.getClass().getName();


    private boolean isGoMain;
    private Handler handler = new Handler();
    private AnimatorSet set;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.account_token_invalid);
//        ButterKnife.bind(this);
//        setTitle("下线通知");
//        init();
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);

        //启动页面动画效果
        doAnim();

        //启动页面的动画监听
        setListener();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 设置沉浸状态栏的方法,需要在setContentView()之后调用
     */
    private void setBar() {
        //兼容5.0+，状态栏设置透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else
            //为了沉浸式状态栏 4.4以上才有
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
    }

    /**
     * 启动页面的加载动画处理
     */
    private void doAnim() {
        //创建一个属性动画集合
        set = new AnimatorSet();
        //加入缩放动画
        ObjectAnimator scalex = ObjectAnimator.ofFloat(ivStart, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivStart, "scaleY", 0f, 1f);
        //透明
        ObjectAnimator alphe = ObjectAnimator.ofFloat(ivStart, "alpha", 0f, 1f);
        //旋转
//        ObjectAnimator rotation = ObjectAnimator.ofFloat(lv, "rotation", 0f, 360f);
//        set.play(scalex).with(scaleY).with(alphe).with(rotation);
        set.play(scalex).with(scaleY).with(alphe);
        set.setDuration(1500);
        set.start();
    }

    /**
     * 动画监听
     */
    private void setListener() {
        if (set != null) {
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    //动画开始时被调用
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    //动画结束时被调用
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startMainActivity();
                        }
                    }, 500);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    // 动画取消时被调用
                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }
    }


    /**
     * 立即点击屏幕也跳转到主界面
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        startMainActivity();
        return super.onTouchEvent(event);
    }

    /**
     * 跳转到主界面的方法
     */
    private void startMainActivity() {
        if (!isGoMain) {
            Intent intent = new Intent(this, WeiboTabActivity.class);
            startActivity(intent);
            isGoMain = true;
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
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
