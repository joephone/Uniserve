package com.transcendence.universe.abp.ui.widget.listview.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.transcendence.universe.R;

public class MulListMainAct extends Activity {
	private ListView listview;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mul_main);
		listview = (ListView) findViewById(R.id.lv_data);
		bt = (Button) findViewById(R.id.bt);

		String[] single_data = new String[30];
		for (int i = 0; i < 30; i++) {
			single_data[i] = "single_" + i;
		}

		// 单选模式的adapter
		listview.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, single_data));
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MulListMainAct.this, MultAct.class);
				startActivity(intent);

			}
		});

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (ListView.CHOICE_MODE_SINGLE == listview.getChoiceMode()) {
					String item_data = (String) listview
							.getItemAtPosition(position);
					Toast.makeText(MulListMainAct.this,
							"当前为单选模式\n选中条目的数据：" + item_data, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
