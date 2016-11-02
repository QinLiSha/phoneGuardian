package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import com.example.test8_10_2.view.ActionBarView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutActivity extends Activity {

	private ActionBarView actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		actionBar = (ActionBarView) findViewById(R.id.actionBarView);
		actionBar.initActionBar("关于我们", R.drawable.btn_homeasup_default, -1,
				onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.iv_left:
				String fromClass = getIntent().getStringExtra("AboutOur");

				if (fromClass != null && fromClass.equals("SettingActivity")) {
					Intent intent = new Intent(AboutActivity.this,
							SettingActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(AboutActivity.this,
							HomeActivity.class);
					startActivity(intent);
				}
				
				// if(fromClass.equals("SettingActivity")){      //会报空指针异常
				// Intent intent =new Intent(AboutActivity.this,SettingActivity.class);
				// }else{
				// Intent intent = new
				// Intent(AboutActivity.this,HomeActivity.class);
				// }

				finish();// 将当前的页面Activity回收Destroy
				break;

			default:
				break;
			}
		}
	};
}
