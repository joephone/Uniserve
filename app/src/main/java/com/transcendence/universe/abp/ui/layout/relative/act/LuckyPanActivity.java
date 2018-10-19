package com.transcendence.universe.abp.ui.layout.relative.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.ui.layout.relative.view.LuckPanLayout;
import com.transcendence.universe.abp.ui.layout.relative.view.RotatePan;

/**
 * Created by Joephone on 2018/10/19 10:32
 * E-Mail Address：joephonechen@gmail.com
 */

public class LuckyPanActivity extends AppCompatActivity implements LuckPanLayout.AnimationEndListener{

    private RotatePan rotatePan;
    private LuckPanLayout luckPanLayout;
    private ImageView goBtn;
    private ImageView yunIv;
    private String[] strs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lucky_pan);
        strs = getResources().getStringArray(R.array.names);
        luckPanLayout = (LuckPanLayout) findViewById(R.id.luckpan_layout);
        luckPanLayout.setAnimationEndListener(this);
        goBtn = (ImageView)findViewById(R.id.go);
        yunIv = (ImageView)findViewById(R.id.yun);
    }

    public void rotation(View view){
        luckPanLayout.rotate(-1,100);
    }

    @Override
    public void endAnimation(int position) {
        Toast.makeText(this,"Position = "+position+","+strs[position],Toast.LENGTH_SHORT).show();
    }


}
