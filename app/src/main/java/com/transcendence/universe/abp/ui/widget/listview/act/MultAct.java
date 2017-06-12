package com.transcendence.universe.abp.ui.widget.listview.act;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.transcendence.universe.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MultAct extends Activity {
    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.lvData)
    ListView lvData;
    private String tag = this.getClass().getName();

    private boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mul_mult);
        ButterKnife.bind(this);

        ArrayList<HashMap<String, String>> mult_data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, String> temp = new HashMap<>();
            temp.put("item", "mult_" + i);
            mult_data.add(temp);
        }

        lvData.getCheckedItemIds();
        SimpleAdapter adapter = new SimpleAdapter(this, mult_data,
                R.layout.mul_item_data, new String[]{"item"},
                new int[]{R.id.tv}) {
            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public long getItemId(int position) {
                return position + 100;
            }
        };
        lvData.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvData.setAdapter(adapter);

        bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < lvData.getAdapter().getCount(); i++) {
                    lvData.setItemChecked(i, state);
                }
                state = !state;
            }
        });

        lvData.addFooterView(new View(this));
        System.out.println(((HeaderViewListAdapter) lvData.getAdapter()).getWrappedAdapter().getCount());
        lvData.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (ListView.CHOICE_MODE_MULTIPLE == lvData.getChoiceMode()) {
                    HashMap<String, String> item = (HashMap<String, String>) lvData
                            .getItemAtPosition(position);
                    Toast.makeText(
                            MultAct.this,
                            "当前为多选模式\n选中的条数：" + lvData.getCheckedItemCount()
                                    + "\n" + "点击的数据：" + item.get("item"), Toast.LENGTH_SHORT)
                            .show();
                    long[] ids = lvData.getCheckedItemIds();
                    for (int i = 0; i < ids.length; i++) {
                        Log.i(tag, ids[i] + "");//如果选中了前四条，打印：100，101，102，103;由前面的adapter getItemId()返回的
                    }
                }
            }
        });
    }

}
