package com.transcendence.universe.abp.classic.toast.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.toast.view.BToast;
import com.umeng.analytics.MobclickAgent;

public class BToastMainAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_toast_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_text:
                BToast.showText(this, "简单提示");
                break;

            case R.id.btn_text_true:
                BToast.showText(this, "简单提示 正确图标", true);
                break;

            case R.id.btn_text_false:
                BToast.showText(this, "简单提示 错误图标", false);
                break;

            case R.id.btn_text_long:
                BToast.showText(this, "简单提示 长~ ", Toast.LENGTH_LONG);
                break;

            case R.id.btn_text_true_long:
                BToast.showText(this, "简单提示 正确图标 长~ ", Toast.LENGTH_LONG, true);
                break;

            case R.id.btn_text_false_long:
                BToast.showText(this, "简单提示 错误图标 长~ ", Toast.LENGTH_LONG, false);
                break;
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
