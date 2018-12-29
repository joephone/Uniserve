package com.transcendence.universe.abp.base.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.Logs;

import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/12/13 16:57
 * E-Mail Address：joephonechen@gmail.com
 * Android 回退键监听
 */

public class ScreenKeyDownActivity extends TitleBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_info);
        ButterKnife.bind(this);
        setTitle("Android回退键监听");
        init();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Logs.i("onKeyDown: keyCode -- " + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Logs.i("KeyEvent.KEYCODE_BACK");
//                break;
                return false;//拦截事件
            case KeyEvent.KEYCODE_MENU:
                Logs.i("KeyEvent.KEYCODE_MENU");
                break;
            case KeyEvent.KEYCODE_HOME:
                Logs.i( "KeyEvent.KEYCODE_HOME");
                // 收不到
                break;
            case KeyEvent.KEYCODE_APP_SWITCH:
                Logs.i( "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                break;
            default:
                break;
        }

        return super.onKeyDown(keyCode, event);

    }

}
