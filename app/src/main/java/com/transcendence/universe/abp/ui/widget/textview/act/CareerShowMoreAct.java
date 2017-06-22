package com.transcendence.universe.abp.ui.widget.textview.act;

import android.app.Activity;
import android.os.Bundle;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.ui.widget.textview.view.CareerShowMoreTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/6/19.     Option + Cmd + L  格式化
 */

public class CareerShowMoreAct extends Activity {

    @Bind(R.id.tvShow)
    CareerShowMoreTextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_career_showmore);
        ButterKnife.bind(this);


        tvShow.setContent("吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光吾若舞兮月无光");
    }


}
