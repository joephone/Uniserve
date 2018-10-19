package com.transcendence.universe.abp.main.act;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.transcendence.universe.utils.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/18 17:05
 * E-Mail Address：joephonechen@gmail.com
 */

public class BaseActivity extends Activity {


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        //设置6.0以后沉浸状体栏的问题
        setBar();
        StatusBarUtil.setTransparentForWindow(this);
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


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
//        SystemBarHelper.immersiveStatusBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        activityStack.add(this);
        //预防系统字体放大字体
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;
//        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //预防系统字体放大字体
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        activityStack.remove(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void gotoActivity(Class<? extends Activity> targetActivity, boolean isFinish) {
        Intent intent = new Intent(BaseActivity.this, targetActivity);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }




}
