package com.transcendence.universe.abp.classic.weiganloopview.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.weiganloopview.view.LoopView;
import com.transcendence.universe.abp.classic.weiganloopview.view.OnItemSelectedListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeiganMainAct extends AppCompatActivity {

    @Bind(R.id.loopView1)
    LoopView loopView1;
    @Bind(R.id.loopView2)
    LoopView loopView2;
    @Bind(R.id.loopView3)
    LoopView loopView3;
    private Toast toast;

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weigan_main);
        ButterKnife.bind(this);


        for (int i = 0; i < 179; i++) {
            list1.add( i+"°");
        }
        for (int i = 0; i < 59; i++) {
            list2.add( i+"′");
        }
        for (int i = 0; i < 59; i++) {
            list3.add( i+"″");
        }
        //设置是否循环播放
//        loopView.setNotLoop();
        //滚动监听
        loopView1.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (toast == null) {
                    toast = Toast.makeText(WeiganMainAct.this, list1.get(index), Toast.LENGTH_SHORT);
                }
                toast.setText(list1.get(index));
                toast.show();
            }
        });
        loopView2.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (toast == null) {
                    toast = Toast.makeText(WeiganMainAct.this, list2.get(index), Toast.LENGTH_SHORT);
                }
                toast.setText(list2.get(index));
                toast.show();
            }
        });
        loopView3.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (toast == null) {
                    toast = Toast.makeText(WeiganMainAct.this, list3.get(index), Toast.LENGTH_SHORT);
                }
                toast.setText(list3.get(index));
                toast.show();
            }
        });
        //设置原始数据
        loopView1.setItems(list1);
        loopView2.setItems(list2);
        loopView3.setItems(list3);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiganMainAct.this, ScrollViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiganMainAct.this, ScrollViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiganMainAct.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

}
