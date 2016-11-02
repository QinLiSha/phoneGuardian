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
	private ImageView[] images = new ImageView[3]; // ����һ��ImageView������
	private boolean isFromClassName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �жϴ��ĸ�ҳ�����ĵ�ǰҳ��
		Intent in = getIntent();
		String className = in.getStringExtra("ClassName");
		if (className != null && className.equals("SettingActivity")) {// ֤����settingActivity�����
			isFromClassName = true;
		}

		SharedPreferences sp = getSharedPreferences("lead_config",
				Context.MODE_PRIVATE);

		boolean isFirst = sp.getBoolean("isFirst", true);// ȡ��ShareP����洢������

		if (!isFirst) {
			Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		} else {
			setContentView(R.layout.activity_lead);
			// UI���û��������˼ UI:user interface
			initUI();// ��ʼ���û�����--����ҳ��Сͼ��+skip(5��Сͼ��+1��skip����)
			init();// ��ʼ������ҳ��ViewPager��ͼ(��ViewPager����Adapter)
			initData();// ��ʼ����������ViewPager����ʾ����ͼ����(��ViewPager��Adapter�������ͼ(ͼƬ))
		}
	}

	/**
	 * ��¼�Ƿ��ǵ�һ�ε�½ҳ��
	 */
	public void saveSharedPreference() {
		// SharedPreferences:�����Դ洢һ�ּ򵥵�����,SqlLite���ݿ�Ĵ洢
		SharedPreferences sp = getSharedPreferences("lead_config",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();// edit��������һ�����Բ���������
		editor.putBoolean("isFirst", false);
		editor.commit();// ���Ҫ����������ݽ���һ���ύ
	}

	private void init() {
		adapter = new MyPagerAdapter(this);
		viewPager.setAdapter(adapter);
		lead_Skip.setOnClickListener((OnClickListener) this);
		viewPager.setOnPageChangeListener(listener);// ����ViewPager�ļ�����

	}

	/**
	 * ��ʼ���ؼ� UI:�û�����
	 */
	private void initUI() {

		images[0] = (ImageView) findViewById(R.id.lead_icon1);
		images[1] = (ImageView) findViewById(R.id.lead_icon2);
		images[2] = (ImageView) findViewById(R.id.lead_icon3);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		lead_Skip = (TextView) findViewById(R.id.lead_skip);
		images[0].setBackgroundResource(R.drawable.adware_style_selected);// ����һ����ť���ó�ѡ��״̬
		lead_Skip.setVisibility(View.INVISIBLE);// ���Խ��ռ��������
	}

	/**
	 * ��ʼ������
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

		adapter.notifyDataSetChanged();// �ǽ�Adapter����һ��ˢ��
	}

	/**
	 * ViewPager�ļ�����
	 */
	private OnPageChangeListener listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) { // ��ҳ�滬����ɺ󣬵��õ�ǰ�ķ���,
												// arg0��������ɺ�ҳ�������ֵ

			lead_Skip.setVisibility(View.INVISIBLE);// �������Ժ�����ָ�����

			if (arg0 >= 2) {
				lead_Skip.setVisibility(View.VISIBLE); // Visiable ��ʾ
														// InVisiable ������λ�ñ���
														// Gone ���أ�λ�ò�����
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
		if (isFromClassName) {// true��Ҫ����Setting����
			Intent intent = new Intent(LeadActivity.this, SettingActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		finish();

	}
}