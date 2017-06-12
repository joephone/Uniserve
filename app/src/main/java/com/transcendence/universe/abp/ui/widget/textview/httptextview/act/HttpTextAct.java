package com.transcendence.universe.abp.ui.widget.textview.httptextview.act;

import android.app.Activity;
import android.os.Bundle;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.ui.widget.textview.httptextview.view.HttpTextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HttpTextAct extends Activity {

    @Bind(R.id.tx_test)
    HttpTextView txTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_textview);
        ButterKnife.bind(this);

//        String test = StringUtils.getString(R.string.catches_msg);
        String test="新版本取消了全球潮汐独立账号密码登录功能，原会员账号已同步至渔获。请下载最新版本的渔获以获取原付费服务及钓点数据。现使用“渔获”账号登录，即可免费享用查看7天内潮汐数据、云同步和天气更新提醒等服务" +
                "\n"+"下载渔获：https://www.catches.com/download/catches";
        txTest.setUrlText(test);


    }
}
