package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.MyPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class LeadActivity extends Activity implements OnClickListener {

	private ViewPager viewPager;
	private MyPagerAdapter adapter;
	private TextView lead_Skip;
	private ImageView[] images = new ImageView[3]; // 定义一个ImageView的数组
	private boolean isFromClassName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 判断从哪个页面进入的当前页面
		Intent in = getIntent();
		String className = in.getStringExtra("ClassName");
		if (className != null && className.equals("SettingActivity")) {// 证明是settingActivity进入的
			isFromClassName = true;
		}

		SharedPreferences sp = getSharedPreferences("lead_config",
				Context.MODE_PRIVATE);

		boolean isFirst = sp.getBoolean("isFirst", true);// 取出ShareP里面存储的数据

		if (!isFirst) {
			Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		} else {
			setContentView(R.layout.activity_lead);
			// UI是用户界面的意思 UI:user interface
			initUI();// 初始化用户界面--引导页面小图标+skip(5个小图标+1个skip文字)
			init();// 初始化引导页面ViewPager视图(给ViewPager设置Adapter)
			initData();// 初始化引导界面ViewPager内显示的视图数据(向ViewPager的Adapter内添加视图(图片))
		}
	}

	/**
	 * 记录是否是第一次登陆页面
	 */
	public void saveSharedPreference() {
		// SharedPreferences:它可以存储一种简单的数据,SqlLite数据库的存储
		SharedPreferences sp = getSharedPreferences("lead_config",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();// edit方法返回一个可以操作的数据
		editor.putBoolean("isFirst", false);
		editor.commit();// 最后要将保存的数据进行一个提交
	}

	private void init() {
		adapter = new MyPagerAdapter(this);
		viewPager.setAdapter(adapter);
		lead_Skip.setOnClickListener((OnClickListener) this);
		viewPager.setOnPageChangeListener(listener);// 设置ViewPager的监听器

	}

	/**
	 * 初始化控件 UI:用户界面
	 */
	private void initUI() {

		images[0] = (ImageView) findViewById(R.id.lead_icon1);
		images[1] = (ImageView) findViewById(R.id.lead_icon2);
		images[2] = (ImageView) findViewById(R.id.lead_icon3);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		lead_Skip = (TextView) findViewById(R.id.lead_skip);
		images[0].setBackgroundResource(R.drawable.adware_style_selected);// 将第一个按钮设置成选中状态
		lead_Skip.setVisibility(View.INVISIBLE);// 可以将空间进行隐藏
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		ImageView imageView = null;

		imageView = (ImageView) getLayoutInflater().inflate(
				R.layout.viewpager_item, null);

		imageView.setBackgroundResource(R.drawable.adware_style_applist);

		adapter.addToAdapterView(imageView);

		imageView = (ImageView) getLayoutInflater().inflate(
				R.layout.viewpager_item, null);

		imageView.setBackgroundResource(R.drawable.adware_style_banner);

		adapter.addToAdapterView(imageView);

		imageView = (ImageView) getLayoutInflater().inflate(
				R.layout.viewpager_item, null);

		imageView.setBackgroundResource(R.drawable.adware_style_creditswall);

		adapter.addToAdapterView(imageView);

		adapter.notifyDataSetChanged();// 是将Adapter进行一个刷新
	}

	/**
	 * ViewPager的监听器
	 */
	private OnPageChangeListener listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) { // 当页面滑动完成后，调用当前的方法,
												// arg0代表滑动完成后页面的索引值

			lead_Skip.setVisibility(View.INVISIBLE);// 滑动完以后把文字给隐藏

			if (arg0 >= 2) {
				lead_Skip.setVisibility(View.VISIBLE); // Visiable 显示
														// InVisiable 隐藏且位置保留
														// Gone 隐藏，位置不保留
			}
			for (int i = 0; i < images.length; i++) {
				images[i]
						.setBackgroundResource(R.drawable.adware_style_default);
			}
			images[arg0]
					.setBackgroundResource(R.drawable.adware_style_selected);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	@Override
	public void onClick(View v) {

		saveSharedPreference();
		if (isFromClassName) {// true需要返回Setting界面
			Intent intent = new Intent(LeadActivity.this, SettingActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		finish();

	}
}