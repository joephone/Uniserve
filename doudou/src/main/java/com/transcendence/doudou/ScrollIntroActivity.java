package com.transcendence.doudou;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.transcendence.library.base.activity.BaseActivity;
import com.transcendence.library.widget.scrollview.AutoScrollView;


public class ScrollIntroActivity extends BaseActivity {

    private AutoScrollView scrollView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.scroll_intro);
//    }

    @Override
    public int getContentView() {
        return R.layout.scroll_intro;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        scrollView = findViewById(R.id.scrollView);
        scrollView.setScrolled(true);
//        scrollView.setSpeed(1000);
    }


}
