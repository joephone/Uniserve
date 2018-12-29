package com.transcendence.universe.abp.main.act;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.transcendence.universe.R;
import com.umeng.analytics.MobclickAgent;

public class MainAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.i(LOG_TAG, "onKeyDown: keyCode -- " + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_BACK");
//                break;
                return false;//拦截事件
            case KeyEvent.KEYCODE_MENU:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_MENU");
                break;
            case KeyEvent.KEYCODE_HOME:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_HOME");
                // 收不到
                break;
            case KeyEvent.KEYCODE_APP_SWITCH:
//                Log.i(LOG_TAG, "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
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
