package com.transcendence.universe.abp.classic.pie.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.pie.view.CircleMenuView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class CircleMenuAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_menu_view);
        CircleMenuView circleMenuView = (CircleMenuView) findViewById(R.id.view);
        circleMenuView.setOnClickListener(new CircleMenuView.onYuanPanClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(CircleMenuAct.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
//       new CircleMenuView(this)
//                .setWidthAndHeight(300, 300)
//                .setCenterText()
//                .setCenterIcon()
//                .setGapColor()
//                .setGapSize()
//                .setMenuIcons()
//                .setMenuTexts()
//                .setMenuTextColor()
//                .setMenuTextSize()
//                .setMenuItemBackground()
//                .setInsideCircleRadius()
//                .setStrokeColor()
//                .setStrokeWidth()
//                .setOnClickListener();

        List<CircleMenuView.Data> list = new ArrayList<>();
        list.add(new CircleMenuView.Data("自驾游", R.drawable.card_info_three, R.drawable.card_info_three));
        list.add(new CircleMenuView.Data("城市合伙人", R.drawable.card_info_four, R.drawable.card_info_four));
        list.add(new CircleMenuView.Data("核心业务", R.drawable.card_info_five, R.drawable.card_info_five));
        list.add(new CircleMenuView.Data("新闻动态", R.drawable.card_info_six, R.drawable.card_info_six));
        list.add(new CircleMenuView.Data("企业风采", R.drawable.card_info_two, R.drawable.card_info_two));
        list.add(new CircleMenuView.Data("公司简介", R.drawable.card_info_one, R.drawable.card_info_one));
//        list.add(new PieView.Data("第六块", R.drawable.card_info_three,R.drawable.card_info_three));
//        list.add(new PieView.Data("第七块", R.drawable.card_info_three,R.drawable.card_info_three));
//        list.add(new PieView.Data("第八块", R.drawable.card_info_three,R.drawable.card_info_three));

        circleMenuView.setList(list);

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
