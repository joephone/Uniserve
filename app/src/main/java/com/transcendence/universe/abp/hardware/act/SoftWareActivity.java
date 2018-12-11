package com.transcendence.universe.abp.hardware.act;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.universe.abp.hardware.bean.PhonoPackageInfo;
import com.transcendence.universe.abp.main.act.TitleBarActivity;


public class SoftWareActivity extends TitleBarActivity{
	//implements View.OnClickListener, AdapterView.OnItemClickListener {

//	ImageView imgSysApp, imgUserApp, imgView;
//	TextView mTxtView;
//	ListView mListView;
//	GridView mGridView;
//	// 换右下角图片的标志
//	boolean mFlag;
//	// 区分系统和用户的标志
//	int flag;
//	// 定义数据源
//	private List<PhonoPackageInfo> arrayList;
//	// 定义适配器
//	private UserAdapter mUserAdapter;
//	private SystemAdapter mSystemAdapter;
//
//	private CharSequence[] items = { "详细信息\n", "卸载程序" };
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_software);
//		init();
//		// 获取数据源
//		arrayList = PackageInfoUtil.getSystemApp(this);
//		// 创建适配器
//		mSystemAdapter = new SystemAdapter(this, arrayList, ViewType.LISTVIEW);
//		mSystemAdapter.setData(arrayList);
//		// 加载适配器
//		mListView.setAdapter(mSystemAdapter);
//		// 应用程序的个数
//		mTxtView.setText("系统程序为：" + arrayList.size());
//	}
//
//	// 初始化
//	public void init() {
//		imgSysApp = (ImageView) findViewById(R.id.img_system);
//		imgSysApp.setOnClickListener(this);
//
//		imgUserApp = (ImageView) findViewById(R.id.img_user);
//		imgUserApp.setOnClickListener(this);
//
//		mTxtView = (TextView) findViewById(R.id.txt_count);
//
//		// 切换ListView和GridView
//		imgView = (ImageView) findViewById(R.id.img_view);
//		imgView.setOnClickListener(this);
//		// 获取ListView,mGridView
//		mListView = (ListView) findViewById(R.id.soft);
//		mGridView = (GridView) findViewById(R.id.soft_gridview);
//
//		// 给ListView和mGridView中的item绑定事件
//		mListView.setOnItemClickListener(this);
//		mGridView.setOnItemClickListener(this);
//
//	}
//
//	/**
//	 * 分别实现系统APP，用户APP和ListView与mGridView视图显示的单击事件
//	 */
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.img_system:
//				imgSysApp.setBackgroundResource(R.drawable.btn_bg);
//				imgUserApp.setBackgroundResource(R.drawable.top_bg);
//
//				arrayList = PackageInfoUtil.getSystemApp(this);
//				// 创建适配器
//				mSystemAdapter = new SystemAdapter(this, arrayList,
//						ViewType.LISTVIEW);
//				mSystemAdapter.setData(arrayList);
//				mListView.setAdapter(mSystemAdapter);
//
//				mTxtView.setText("系统程序为：" + arrayList.size());
//
//				flag = R.id.img_system;
//				break;
//
//			case R.id.img_user:
//				imgUserApp.setBackgroundResource(R.drawable.btn_bg);
//				imgSysApp.setBackgroundResource(R.drawable.top_bg);
//
//				arrayList = PackageInfoUtil.getUserApp(this);
//				mUserAdapter = new UserAdapter(this, arrayList, ViewType.LISTVIEW);
//				mUserAdapter.setData(arrayList);
//				mListView.setAdapter(mUserAdapter);
//
//				// 显示下边的程序数量
//				mTxtView.setText("用户应用程序为：" + arrayList.size());
//
//				flag = R.id.img_user;
//				break;
//			case R.id.img_view:
//				if (mFlag) {
//					// 换右下角的图片
//					imgView.setImageResource(R.drawable.grids);
//
//					mGridView.setVisibility(View.VISIBLE);
//					mListView.setVisibility(View.GONE);
//					if (flag == R.id.img_system) {
//						mGridView.setAdapter(mSystemAdapter);
//					} else if (flag == R.id.img_user) {
//						mGridView.setAdapter(mUserAdapter);
//					}
//
//				} else {
//
//					imgView.setImageResource(R.drawable.list);
//
//					mListView.setVisibility(View.VISIBLE);
//					mGridView.setVisibility(View.GONE);
//
//					if (flag == R.id.img_system) {
//						mListView.setAdapter(mSystemAdapter);
//					} else if (flag == R.id.img_user) {
//						mListView.setAdapter(mUserAdapter);
//					}
//				}
//				mFlag = !mFlag;
//				break;
//		}
//	}
//
//	/**
//	 * 枚举类型，用法与类相同
//	 */
//	enum ViewType {
//		LISTVIEW, GRIDVIEW;
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//							long id) {
//		final int pos = position;
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setTitle(R.string.title);
//		builder.setItems(items, new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(SoftWareActivity.this, "被点击了。。" + which,
//						Toast.LENGTH_LONG).show();
//				switch (which) {
//					case 0:
//						showAppInfo(pos);
//						break;
//					case 1:
//						Uri uri = Uri.parse("package:"
//								+ arrayList.get(pos).getPkgname());
//						Intent intent = new Intent(Intent.ACTION_DELETE, uri);
//						// startActivity(intent);
//						startActivityForResult(intent, 1);
//						break;
//				}
//			}
//		});
//		builder.setNegativeButton(R.string.cancel, null);
//		builder.show();
//	}
//
//	public void showAppInfo(int position) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(
//				SoftWareActivity.this);
//
//		builder.setTitle(R.string.title_1);
//		if (flag == R.id.img_system) {
//			builder.setIcon(arrayList.get(position).getIcon());
//			builder.setMessage("包名:" + arrayList.get(position).getPkgname()
//					+ "\n" + "版本名称:" + arrayList.get(position).getVersionName()
//					+ "\n" + "程序号:" + arrayList.get(position).getVersionId()
//					+ "\n" + "标签:" + arrayList.get(position).getLabel());
//		}
//		if (flag == R.id.img_user) {
//			builder.setIcon(arrayList.get(position).getIcon());
//			builder.setMessage("包名:" + arrayList.get(position).getPkgname()
//					+ "\n" + "版本名称:" + arrayList.get(position).getVersionName()
//					+ "\n" + "程序号:" + arrayList.get(position).getVersionId()
//					+ "\n" + "标签:" + arrayList.get(position).getLabel());
//		}
//		builder.setPositiveButton(R.string.ok, null);
//		builder.show();
//	}
//
//	/**
//	 * 更新软件
//	 */
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (flag == R.id.img_system) {
//			arrayList = PackageInfoUtil.getSystemApp(this);
//			mSystemAdapter.setData(arrayList);
//			mTxtView.setText("系统程序为：" + arrayList.size());
//		} else if (flag == R.id.img_user) {
//			arrayList = PackageInfoUtil.getUserApp(this);
//			mUserAdapter.setData(arrayList);
//			mTxtView.setText("用户程序为：" + arrayList.size());
//		}
//	}
}
