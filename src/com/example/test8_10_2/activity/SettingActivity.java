package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.util.NotificationUtil;
import com.example.test8_10_2.view.ActionBarView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingActivity extends BaseActivity {

	private ActionBarView actionBarView;
	private ToggleButton tb_Notification;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initActionBar("设置", R.drawable.btn_homeasup_default,-1, onClickListener);
	/**
	 	//上面封装的方法取代了下面的方法
	 	actionBarView = (ActionBarView) findViewById(R.id.actionBarView);
	    actionBarView.initActionBar("设置", R.drawable.btn_homeasup_default, -1,onClickListener);
	 */
	
		
		tb_Notification = (ToggleButton) findViewById(R.id.tb_notification);
		tb_Notification
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							NotificationUtil
									.startNotification(SettingActivity.this);
						} else {
							NotificationUtil
									.cancleNotification(SettingActivity.this);
						}

					}
				});
	}

	public void hitSettingItem(View v) {
		int id = v.getId();
		switch (id) {

		case R.id.rel_setting_help:

			SharedPreferences sp = getSharedPreferences("lead_config",
					Context.MODE_PRIVATE);
			Editor editor = sp.edit();// edit方法返回一个可以操作的数据
			editor.putBoolean("isFirst", true);
			editor.commit();// 最后要将保存的数据进行一个提交
			Toast.makeText(SettingActivity.this, "帮助", 0).show(); //这个是点击时的一个说明,会有一个方框显示"帮助"文本
			Intent intent = new Intent(SettingActivity.this, LeadActivity.class);
			intent.putExtra("ClassName", SettingActivity.this.getClass()
					.getSimpleName());
			startActivity(intent);
			break;
		case R.id.rel_setting_about:
			Intent in = new Intent(SettingActivity.this, AboutActivity.class);
			in.putExtra("AboutOur", SettingActivity.this.getClass()
					.getSimpleName());
			startActivity(in);

		default:
			break;
		}
	}

	public OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			int id = v.getId();

			switch (id) {
			case R.id.iv_left:
				startActivity(HomeActivity.class);
//				Intent intent = new Intent(SettingActivity.this,HomeActivity.class);
//				startActivity(intent);
				finish();
				break;
			// case R.id.iv_right:
			// Intent intent1 = new Intent(SettingActivity.this,
			// HomeActivity.class);
			// startActivity(intent1);
			// finish();
			// break;
			}
		}
	};
}
