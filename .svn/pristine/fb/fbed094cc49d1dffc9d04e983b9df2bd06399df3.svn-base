package com.example.test8_10_2.activity;

import java.util.List;
import java.util.Map;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.RunningAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.RunningAppInfo;
import com.example.test8_10_2.util.AppInfoManager;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.MemoryManager;
import com.example.test8_10_2.util.SystemManger;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SpeedUpActivity extends BaseActivity {

	private TextView tv_phoneName;
	private TextView tv_phoneModel;
	private ProgressBar pb_progress;
	private TextView phone_ram;
	private ListView speedup_listView;
	private ProgressBar pb_progress_list;
	private Button btn_show_progress;
	private RunningAdapter adapter;
	private Map<Integer, List<RunningAppInfo>> appInfos = null;

	private Button btn_oneKeySpeed;
	private CheckBox ck_speedup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speed_up);
		initActionBar("手机加速", R.drawable.btn_homeasup_default, -1, onclick);
		speedup_listView = (ListView) findViewById(R.id.speedup_listView);
		adapter = new RunningAdapter(this);
		speedup_listView.setAdapter(adapter);
		initView();
		loadData();
	}

	private void initView() {

		tv_phoneName = (TextView) findViewById(R.id.tv_phoneName);
		tv_phoneModel = (TextView) findViewById(R.id.tv_phoneModel);
		pb_progress = (ProgressBar) findViewById(R.id.pb_progress);
		phone_ram = (TextView) findViewById(R.id.phone_ram);
		pb_progress_list = (ProgressBar) findViewById(R.id.pb_progress_list);
		btn_show_progress = (Button) findViewById(R.id.btn_show_progress);
		btn_oneKeySpeed = (Button) findViewById(R.id.btn_oneKeySpeed);
		ck_speedup = (CheckBox) findViewById(R.id.ck_speedup);
		btn_show_progress.setOnClickListener(onclick);
		btn_oneKeySpeed.setOnClickListener(onclick);
		ck_speedup.setOnCheckedChangeListener(onCheckedChangeListener);
		initPhoneData();
		initRamData();
	}

	private void initRamData() {
		float totalMemory = MemoryManager.getTotalMemoryPhoneRam();// 总的系统内存
		float freeMemory = MemoryManager.getFreeMemoryRam(SpeedUpActivity.this);// 可用内存的大小
		float useMemory = totalMemory - freeMemory;
		float usePersent = useMemory / totalMemory;
		int user100 = (int) (usePersent * 100);
		pb_progress.setProgress(user100);// 设置跳出当前的进度
		phone_ram.setText("可用内存" + CommonUtil.getFileInfo((long) freeMemory)
				+ "/" + CommonUtil.getFileInfo((long) totalMemory));
	}

	/**
	 * 设置手机的信息
	 */
	private void initPhoneData() {
		tv_phoneName.setText(SystemManger.getPhoneName());
		tv_phoneModel.setText(SystemManger.getSystemPhoneModel());

	}

	private void loadData() {

		speedup_listView.setVisibility(View.INVISIBLE);
		pb_progress_list.setVisibility(View.VISIBLE);
		new Thread() {
			public void run() {
				appInfos = AppInfoManager.getAppInfoManager(
						SpeedUpActivity.this).getRunningAppInfos();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						speedup_listView.setVisibility(View.VISIBLE);
						pb_progress_list.setVisibility(View.INVISIBLE);
						initRamData();
						adapter.setDataToAdapter(appInfos
								.get(AppInfoManager.USER_TYPE_FLAG));

						adapter.setState(RunningAdapter.USER_FLAG);
						adapter.notifyDataSetChanged();
					}
				});
			};

		}.start();
	}

	private OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			int id = v.getId();
			switch (id) {
			case R.id.btn_show_progress:
				if (appInfos != null) {
					switch (adapter.getState()) {
					case RunningAdapter.USER_FLAG:
						adapter.setDataToAdapter(appInfos
								.get(AppInfoManager.SYSTEM_TYPE_FLAG));
						adapter.setState(RunningAdapter.SYSTEM_FLAG);
						btn_show_progress.setText("显示应用程序");
						break;
					case RunningAdapter.SYSTEM_FLAG:
						adapter.setDataToAdapter(appInfos
								.get(AppInfoManager.USER_TYPE_FLAG));
						adapter.setState(RunningAdapter.USER_FLAG);
						btn_show_progress.setText("显示系统程序");
						break;
					}
					adapter.notifyDataSetChanged();
				}
				break;

			case R.id.btn_oneKeySpeed:
				List<RunningAppInfo> listAllData = adapter.getDataList();
				for (RunningAppInfo run : listAllData) {
					if (run.isClear()) {
						String packageName = run.getPacketName();
						AppInfoManager.getAppInfoManager(SpeedUpActivity.this)
								.killProcesses(packageName);
					}

				}

				loadData();
				ck_speedup.setChecked(false);

				break;
			case R.id.iv_left:
				startActivity(HomeActivity.class);
				finish();
				break;
			}
		}
	};
	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			List<RunningAppInfo> listAllData = adapter.getDataList();
			for (RunningAppInfo run : listAllData) {
				run.setClear(isChecked);
			}
			adapter.notifyDataSetChanged();
		}
	};

}
