package com.transcendence.universe.abp.ui.widget.textview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.universe.R;


/**
 * @author yangyu 功能描述：标题按钮上的弹窗（继承自PopupWindow）
 */
public class CareerShowMoreTextView extends LinearLayout {
	//    用来标记是否为展开状态
	private String tag = this.getClass().getName();
	private int hideOrShow = 0;
	private TextView textView;
	private ImageView image;

	public CareerShowMoreTextView(Context context) {
		super(context);
	}

	public CareerShowMoreTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
//        实例化layoutInflater对象，获取到布局填充服务
		LayoutInflater layoutInflater = (LayoutInflater)context.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        填充自定义的布局xml文件
		layoutInflater.inflate(R.layout.view_career_showmore,this);
		textView = (TextView)findViewById(R.id.content);
		image=(ImageView) findViewById(R.id.hide_show);


//        隐藏或显示
		hideOrShow();
	}
	//    创建setContent方法为TextView填充内容
	public void setContent(String content) {
		textView.setText(content);
	}

	public void hideOrShow() {
		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//由hideOrShow的值确定按钮和textview的状态
				if (hideOrShow == 0) {
					image.setImageResource(R.drawable.ic_career_up);
					textView.setMaxLines(100);
					hideOrShow = 2;
				}else if(hideOrShow==2){
					image.setImageResource(R.drawable.ic_career_down);
					textView.setMaxLines(1);
					hideOrShow = 1;
				}else if(hideOrShow==1){
					image.setImageResource(R.drawable.ic_career_up);
					textView.setMaxLines(100);
					hideOrShow=2;
				}
			}
		});
	}
}
