package com.example.test8_10_2.activity;

import java.util.List;
import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.AppAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.AppInfo;
import com.example.test8_10_2.util.AppInfoManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;

public class SoftMgrShowActivity extends BaseActivity {

	private int id;
	private String title;
	private ListView lv_soft_show;
	private ProgressBar pb_soft_show_loading;
	private CheckBox cb_soft_show;
	private Button btn_show_progress_soft;
	private AppAdapter adapter;
	private AppInfoReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_mgr_show);
		int id = getIntent().getIntExtra("viewId", R.id.soft_classlist_all);
		switch (id) {
		case R.id.soft_classlist_all:
			title = "全部软件";
			break;
		case R.id.soft_classlist_system:
			title = "系统软件";
			break;
		case R.id.soft_classlist_user:
			title = "用户软件";
			break;
		}
		initActionBar(title, R.drawable.btn_homeasup_default, -1, onClickListener);
		this.id = id;
		initView();
		adapter = new AppAdapter(this);
		lv_soft_show.setAdapter(adapter);
		initLoadData();

	}

	private List<AppInfo> listInfo = null;

	private void initLoadData() {
		new Thread() {
			@Override
			public void run() {
				pb_soft_show_loading.setVisibility(View.VISIBLE);
				lv_soft_show.setVisibility(View.INVISIBLE);
				switch (id) {
				case R.id.soft_classlist_all:
					listInfo = AppInfoManager.getAppInfoManager(
							SoftMgrShowActivity.this).getAllPackageInfos(true);
					break;
				case R.id.soft_classlist_system:
					listInfo = AppInfoManager.getAppInfoManager(
							SoftMgrShowActivity.this)
							.getSystemPackageInfos(true);
					break;
				case R.id.soft_classlist_user:
					listInfo = AppInfoManager.getAppInfoManager(
							SoftMgrShowActivity.this).getUserPackageInfos(true);
					break;
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						adapter.setDataToAdapter(listInfo);
						adapter.notifyDataSetChanged();
						pb_soft_show_loading.setVisibility(View.INVISIBLE);
						lv_soft_show.setVisibility(View.VISIBLE);
					}
				});
			}
		}.start();
		System.out.println(listInfo);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		lv_soft_show = (ListView) findViewById(R.id.lv_soft_show);
		pb_soft_show_loading = (ProgressBar) findViewById(R.id.pb_soft_show_loading);
		cb_soft_show = (CheckBox) findViewById(R.id.cb_soft_show);
		btn_show_progress_soft = (Button) findViewById(R.id.btn_show_progress_soft);
		btn_show_progress_soft.setOnClickListener(onClickListener);
		cb_soft_show.setOnCheckedChangeListener(onCheckedChangeListener);
	
		receiver = new AppInfoReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_DELETE);
		filter.addAction(AppInfoReceiver.APP_DEL);
		filter.addDataScheme("package");
		registerReceiver(receiver, filter);
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			List<AppInfo> appinfos = adapter.getDataList();
			for (AppInfo ai : appinfos) {
				ai.setDel(isChecked);
			}
			adapter.notifyDataSetChanged();
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				startActivity(SoftMgrActivity.class);
				finish();
				break;
			case R.id.btn_show_progress_soft:
				List<AppInfo> liInfos = adapter.getDataList();
				for (AppInfo ai : liInfos) {
					String packageName = ai.getPackageInfo().packageName;
					if (ai.isDel()) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_DELETE);// 删除apk安装包
						intent.setData(Uri.parse("package:" + packageName));
						startActivity(intent);
					}
				}
				break;
			}
		}
	};

	public class AppInfoReceiver extends BroadcastReceiver {
		public static final String APP_DEL = "com.example.test8_10_2";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_UNINSTALL_PACKAGE)// 当前程序的adk文件卸载完了后得到的响应
					|| action.equals(APP_DEL)) {
				initLoadData();
			}
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
