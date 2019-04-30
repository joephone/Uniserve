package com.transcendence.universe.abp.main.act;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.transcendence.universe.R;
import com.transcendence.universe.utils.Logs;
import com.transcendence.universe.utils.StatusBarUtil;
import com.transcendence.universe.utils.permission.PermissionPool;

import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/18 17:05
 * E-Mail Address：joephonechen@gmail.com
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        //设置6.0以后沉浸状体栏的问题
        setBar();
        StatusBarUtil.setTransparentForWindow(this);

        init();
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

    protected void init() {
    }


    private View mProgressView;
    private View mContentView;

    /**
     * 弹出登录对话框
     */
//    private View getDialog() {
//        mProgressView = View.inflate(this, R.layout.load_progress, null);
//        return mProgressView;
//    }
//
//    public void showDialog() {
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup
//                .LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        ((ViewGroup) mContentView).addView(getDialog(), lp);
//    }
//
//    public void dismissDialog() {
//        if (null != mProgressView) {
//            ((ViewGroup) mContentView).removeView(mProgressView);
//            mProgressView = null;
//        }
//    }


    public boolean checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }



    /**
     * android6.0权限请求
     * @param code             权限标记Code
     * @param permissionName    权限名称
     */
    public void onPermissionRequest(@PermissionPool.PermissionCode int code, @PermissionPool.PermissionName String permissionName){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Logs.logI("22以下");
            return ;
        }
        if(ContextCompat.checkSelfPermission(this, permissionName)== PackageManager.PERMISSION_GRANTED){
            Logs.logI("有权限");
            //有权限
            onPermissionsGranted(code);
        }else{
            Logs.logI("没有权限,开始申请");
            //没有权限,开始申请
            ActivityCompat.requestPermissions(this,new String[]{permissionName},code);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //授权成功
            onPermissionsGranted(requestCode);
        }else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
            //授权失败
            onPermissionsDenied(requestCode);
        }
    }

    /**
     * 有授权执行的方法(子类重写)
     */
    public void onPermissionsGranted(int requestCode) {

    }

    /**
     * 没有授权执行的方法(子类重写)
     */
    public void onPermissionsDenied(int requestCode) {
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
