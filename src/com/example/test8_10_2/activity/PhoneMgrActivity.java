package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.PhoneMgrAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.PhoneInfo;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.MemoryManager;
import com.example.test8_10_2.util.PhoneManager;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneMgrActivity extends BaseActivity {

	private ListView lv_battary;
	private PhoneMgrAdapter adapter;
	private ProgressBar pb_load;

	private int currentBattery;// 当前电量
	private int temperatureBattery;// 电池温度
	private TextView tv_battery;// 电池百分比
	private ProgressBar pb_battery;// 电池电量进度
	private BatteryReceiver onReceiver;
	private LinearLayout lla_battery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_mgr);
		initActionBar("手机检测", R.drawable.btn_homeasup_default, -1,
				onClickListener);
		initMainUI();// 初始化UI控件
		lv_battary = (ListView) findViewById(R.id.lv_battary);
		adapter = new PhoneMgrAdapter(this);
		lv_battary.setAdapter(adapter);
		new Thread() {
			public void run() {
				asyncloadData();
			};
		}.start();

	}

	private void initMainUI() {

		pb_load = (ProgressBar) findViewById(R.id.pb_battery_num);// 下放加载数据的进度条
		tv_battery = (TextView) findViewById(R.id.tv_battery_process);// 电量电池的显示
		pb_battery = (ProgressBar) findViewById(R.id.pb_battery);
		lla_battery = (LinearLayout) findViewById(R.id.lla_battery);
		lla_battery.setOnClickListener(onClickListener);// 之前没有写这句话就没有办法点击....................!!!!!!!!!!!!!!!!!!!!!!!!!!!
		onReceiver = new BatteryReceiver();// 广播
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);// 过滤电池的广播
		registerReceiver(onReceiver, filter);// 注册一个广播
	}

	private void asyncloadData() {
		lv_battary.setVisibility(View.INVISIBLE);// ListView暂时隐藏
		pb_load.setVisibility(View.VISIBLE);// 进度条显示
		String title;
		String content;
		Drawable icon;
		PhoneManager manager = PhoneManager.getInstance(this);

		title = "设备名称 : " + manager.getPhoneName1();
		content = "系统版本 : " + manager.getPhoneSystemVersion();
		icon = getResources().getDrawable(R.drawable.setting_info_icon_version);
		final PhoneInfo phoneInfo1 = new PhoneInfo(title, content, icon);

		title = "全部可用内存 : "
				+ CommonUtil
						.getFileInfo(MemoryManager.getTotalMemoryPhoneRam());
		content = "可运行内存 : "
				+ CommonUtil.getFileInfo(MemoryManager
						.getFreeMemoryRam(PhoneMgrActivity.this));
		icon = getResources().getDrawable(R.drawable.setting_info_icon_space);
		final PhoneInfo phoneInfo2 = new PhoneInfo(title, content, icon);

		title = "CPU名称 : " + manager.getPhoneCpuName();
		content = "CPU数量 : " + manager.getPhoneCpuNumber();
		icon = getResources().getDrawable(R.drawable.setting_info_icon_cpu);
		final PhoneInfo phoneInfo3 = new PhoneInfo(title, content, icon);
		/**
		 * 手机分辨率不能用,会报错,所以设置了一个固定值
		 */
		title = "手机分辨率 : " + manager.getResolution();
		content = "像素分辨率 : " + manager.getResolution();
		icon = getResources().getDrawable(R.drawable.setting_info_icon_camera);
		final PhoneInfo phoneInfo4 = new PhoneInfo(title, content, icon);

		// title = "手机分辨率 : " + "1300p";
		// content = "像素分辨率 : " + "";
		// icon =
		// getResources().getDrawable(R.drawable.setting_info_icon_camera);
		// final PhoneInfo phoneInfo4 = new PhoneInfo(title, content, icon);

		title = "基带版本 : " + manager.getPhoneSystemBasebandVersion();
		content = "是否有ROOT权限:" + (manager.isRoot() ? "是" : "否");
		icon = getResources().getDrawable(R.drawable.setting_info_icon_root);
		final PhoneInfo phoneInfo5 = new PhoneInfo(title, content, icon);

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				adapter.addDataToAdapter(phoneInfo1);
				adapter.addDataToAdapter(phoneInfo2);
				adapter.addDataToAdapter(phoneInfo3);
				adapter.addDataToAdapter(phoneInfo4);
				adapter.addDataToAdapter(phoneInfo5);
				lv_battary.setVisibility(View.VISIBLE);// ListView暂时隐藏
				pb_load.setVisibility(View.INVISIBLE);// 进度条显示
				adapter.notifyDataSetChanged();
			}
		});
	}

	/**
	 * BroadcastReceiver四大组件第二个广播通知 onReceive方法
	 * 
	 * @author Administrator one of a kind 2016-8-19下午3:10:03
	 */
	public class BatteryReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				Bundle bundle = intent.getExtras();
				int maxBattery = (Integer) bundle
						.get(BatteryManager.EXTRA_SCALE);// 电池的总量大小
				currentBattery = (Integer) bundle
						.get(BatteryManager.EXTRA_LEVEL);// 当前电量
				temperatureBattery = (Integer) bundle
						.get(BatteryManager.EXTRA_TEMPERATURE);// 电池的温度
				pb_battery.setMax(maxBattery);
				pb_battery.setProgress(currentBattery);
				int use100 = currentBattery * 100 / maxBattery;
				tv_battery.setText(use100 + "%");
			}

		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(onReceiver);

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.iv_left:
				startActivity(HomeActivity.class);
				finish();
				break;

			case R.id.lla_battery:
				// Toast.makeText(PhoneMgrActivity.this, "帮助", 0).show();
				// //这个是点击时的一个说明,会有一个方框显示"帮助"文本
				showDetails();
			default:
				break;
			}
		}

		/**
		 * 点击电池时显现出来的详情
		 */
		private void showDetails() {
			new AlertDialog.Builder(PhoneMgrActivity.this)
					.setTitle("电池详情")
					.setItems(
							new String[] {
									"当前电量百分比: " + currentBattery + "%",
									"当前手机温度: " + temperatureBattery / 10
											+ "摄氏度" }, null).show();
		}

	};

}
