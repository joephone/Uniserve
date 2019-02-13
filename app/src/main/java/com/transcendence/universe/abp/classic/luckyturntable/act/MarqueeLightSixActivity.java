package com.transcendence.universe.abp.classic.luckyturntable.act;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.bean.Prize;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewOne;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewSix;
import com.transcendence.universe.abp.main.act.TitleBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joephone on 2018/12/18 10:39
 * E-Mail Address：joephonechen@gmail.com
 */

public class MarqueeLightSixActivity extends TitleBarActivity {
    NineGridViewSix nl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nine_grid_lucky6);
        nl = (NineGridViewSix) findViewById(R.id.nl);

        int[] prizesIcon = {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4,
                R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.n1};
        final List<Prize> prizes = new ArrayList<Prize>();
        for (int x = 0; x < 9; x++) {
            Prize lottery = new Prize();
            lottery.setId(x + 1);
            lottery.setName("Lottery" + (x + 1));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), prizesIcon[x]);
            lottery.setIcon(bitmap);
            if ((x + 1) % 2 == 0) {
                lottery.setBgColor(0xff4fccee);
            } else if (x == 4) {
                lottery.setBgColor(0xffffffff);
            } else {
                lottery.setBgColor(0xff00ff34);
            }

            prizes.add(lottery);
        }
        nl.setPrizes(prizes);
        nl.setOnTransferWinningListener(new NineGridViewSix.OnTransferWinningListener() {

            @Override
            public void onWinning(int position) {
                Toast.makeText(getApplicationContext(), prizes.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
