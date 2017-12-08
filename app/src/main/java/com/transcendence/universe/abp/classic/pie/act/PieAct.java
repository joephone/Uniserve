package com.transcendence.universe.abp.classic.pie.act;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.pie.view.PieView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PieAct extends ActionBarActivity {


    @Bind(R.id.pv)
    PieView pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_main);
        ButterKnife.bind(this);

        List<PieView.Data> list = new ArrayList<>();
        list.add(new PieView.Data("自驾游", R.drawable.card_info_two, R.drawable.card_info_two));
        list.add(new PieView.Data("城市合伙人", R.drawable.card_info_four, R.drawable.card_info_four));
        list.add(new PieView.Data("核心业务", R.drawable.card_info_five, R.drawable.card_info_five));
        list.add(new PieView.Data("新闻动态", R.drawable.card_info_six, R.drawable.card_info_six));
        list.add(new PieView.Data("企业风采", R.drawable.card_info_one, R.drawable.card_info_one));
        list.add(new PieView.Data("公司简介", R.drawable.card_info_three, R.drawable.card_info_three));
//        list.add(new PieView.Data("第六块", R.drawable.card_info_three,R.drawable.card_info_three));
//        list.add(new PieView.Data("第七块", R.drawable.card_info_three,R.drawable.card_info_three));
//        list.add(new PieView.Data("第八块", R.mipmap.icon_normal,R.mipmap.icon_select));
        pv.setList(list);
        pv.setOnItemSelectListener(new PieView.OnItemSelectListener() {
            @Override
            public void selectPosition(int position, PieView pieView) {
                if (pieView.getList() != null) {
                    Toast.makeText(PieAct.this, pieView.getList().get(position).name, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
