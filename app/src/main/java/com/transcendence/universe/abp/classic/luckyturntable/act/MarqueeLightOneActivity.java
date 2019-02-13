package com.transcendence.universe.abp.classic.luckyturntable.act;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.bean.Prize;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewOne;
import com.transcendence.universe.abp.main.act.TitleBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joephone on 2018/12/18 10:39
 * E-Mail Address：joephonechen@gmail.com
 */

public class MarqueeLightOneActivity extends TitleBarActivity {
    NineGridViewOne nl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nine_grid_lucky1);
        nl = (NineGridViewOne) findViewById(R.id.lotteryView);

        int[] prizesIcon = {R.mipmap.lucky_prize1, R.mipmap.lucky_prize2, R.mipmap.lucky_prize3, R.mipmap.lucky_prize4,
                R.mipmap.lucky_prize1, R.mipmap.lucky_prize4, R.mipmap.lucky_prize3, R.mipmap.lucky_prize2, R.mipmap.lucky_prize1};
        final List<Prize> prizes = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            Prize lottery = new Prize();
            lottery.setId(x + 1);
            lottery.setName("Lottery" + (x + 1));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), prizesIcon[x]);
            lottery.setIcon(bitmap);
            if ((x + 1) % 2 == 0) {
                lottery.setBgColor(0xff4fccee);
            } else if (x == 4) {
                lottery.setBgColor(0xffffffff);  //bg_lucky_draw_btn_selector
            } else {
                lottery.setBgColor(0xff00ff34);
            }

            prizes.add(lottery);
        }
        nl.setPrizes(prizes);
        nl.setOnTransferWinningListener(new NineGridViewOne.OnTransferWinningListener() {

            @Override
            public void onWinning(int position) {
                Toast.makeText(getApplicationContext(), prizes.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
