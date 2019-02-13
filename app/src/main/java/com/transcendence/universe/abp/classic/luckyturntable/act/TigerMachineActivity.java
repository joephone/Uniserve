package com.transcendence.universe.abp.classic.luckyturntable.act;

import java.util.Random;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.luckyturntable.view.NineGridViewThreeItem;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.Logs;

public class TigerMachineActivity extends TitleBarActivity {
	private GridView gridView;
	private int MARK_LUCKY=-1;
	private int currentIndex=-1;
	// 未开始抽奖时的图片
	private int[] imgs1 = { R.drawable.m1, R.drawable.m2, R.drawable.m3,
			R.drawable.m8,R.drawable.ic_launcher, R.drawable.m4,
			R.drawable.m7, R.drawable.m6, R.drawable.m5};
	// 开始抽奖时的图片
	private int[] imgs2 = { R.drawable.n1, R.drawable.n2, R.drawable.n3,
			R.drawable.n8, R.drawable.ic_launcher, R.drawable.n4,
			R.drawable.n7, R.drawable.n6, R.drawable.n5 };
	// 对应转盘id的数组
	private int[] array = {0, 1, 2, 5, 8, 7, 6, 3 }; //0, 1, 2, 5, 8, 7, 6, 3    //0, 1, 2, 3, 5, 6, 7, 8
	// Runnable接口
	private MyRunnable mMyRunnable;
	// 代表从0到8的9个图片序号
	private int num;

	// 开始的时间
	private int startTime;
	// 结束的时间
	private int stopTime;
	private Adpter adpter;
	//开始的按钮 
//	private Button start_btn;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tiger_machine_main);
		context = this;
    	initview(); // 初始化
      


	}

	private void initview() {
//		start_btn = (Button) findViewById(R.id.start_btn);
		gridView = (GridView) findViewById(R.id.gridview); 
	 	
		adpter = new Adpter();
		gridView.setAdapter(adpter);
		
		
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Change(array[num]);  //改变背景色
			num++;                 //依次下一个
			//如果到了最后一个item，则循环
			if (num >= 8) {
				num = 0;
			}

		};
	};

	class MyRunnable implements Runnable {
		@Override
		public void run() {
			handler.sendEmptyMessage(0);  //发送消息
			//如果到达指定的时间,则停止
			if (startTime >= 1000 && MARK_LUCKY == currentIndex) {   //stopTime
				handler.removeCallbacks(mMyRunnable);
				//提示中奖消息
				Logs.logE("num---"+num);
				Logs.logE("array[num]---"+array[num]);
				if (array[num] < 4) {
					String text = array[num] + 1 + "";
					Toast.makeText(context, "恭喜你中了" + text, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, "恭喜你中了" + array[num], Toast.LENGTH_SHORT).show();
				}
                
				startTime = 0;
				stopTime = 0;
				return;
			}
			//每隔100毫秒运行一次
			handler.postDelayed(mMyRunnable, 100);
			startTime += 100;
		}
	}

	private void Change(int id) {
		currentIndex = id;
		for (int i = 0; i < gridView.getChildCount(); i++) {
			if (i == id) {
 			//如果是选中的，则改变图片为数组2中的图片  mLuckyDrawItem[i].setSelected(true);;  setBackgroundResource(imgs2[id]);
			((NineGridViewThreeItem) (gridView.getChildAt(i).findViewById(R.id.img))).setFocus(false);
			} else if (i == 4) {
 		    //如果是到了中间那个，则跳出
				continue;
			} else {
 		    //未选中的就设置为数组1中的图片  setBackgroundResource(imgs1[i]);
				((NineGridViewThreeItem) (gridView.getChildAt(i).findViewById(R.id.img))).setFocus(true);
			}
		}
	}

	class Adpter extends BaseAdapter {

		@Override
		public int getCount() {
			return imgs1.length;
		}

		@Override
		public Object getItem(int position) {
			return imgs1[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
     		View view = View.inflate(TigerMachineActivity.this, R.layout.tiger_machine_item,null);
			NineGridViewThreeItem img = (NineGridViewThreeItem) view.findViewById(R.id.img);
//        	img.setBackgroundResource(imgs1[position]);
        	if(position == 4){
        		img.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						//定义一个随机数最为结束的时间，这里是2到6秒
//						stopTime = new Random().nextInt(1000 * 5) + 2000;

						MARK_LUCKY = new Random().nextInt(8);
						Logs.logE("MARK_LUCKY----"+MARK_LUCKY);
						Logs.logE("startTime----"+startTime);
						Logs.logE("stopTime----"+new Random().nextInt(1000 * 5) + 2000);
						//开启线程
						mMyRunnable = new MyRunnable();
						new Thread(mMyRunnable).start();
					}
				});

			}
			return view;
		}
	}



}
