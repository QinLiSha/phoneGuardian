package com.example.test8_10_2.activity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.ClearMgrAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.ClearBean;
import com.example.test8_10_2.util.ClearManager;
import com.example.test8_10_2.util.CommonUtil;
import com.example.test8_10_2.util.FileManager;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ClearActivity extends BaseActivity {
	private ListView lv_clear;
	private ClearMgrAdapter adapter;
	private ProgressBar pb_clear;
	private long total_size = 0;// 记录总的大小
	private TextView tv_clear_file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clear);
		initActionBar("垃圾清理", R.drawable.btn_homeasup_default, -1, onClickListener);
		initMainUI();
		adapter = new ClearMgrAdapter(this);
		lv_clear.setAdapter(adapter);
		try {
			asyncTaskLoadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMainUI() {
		lv_clear = (ListView) findViewById(R.id.lv_clear);
		pb_clear = (ProgressBar) findViewById(R.id.pb_clear);
		tv_clear_file = (TextView) findViewById(R.id.tv_clear_file);
	}

	@Override
	protected void myHandlerMessage(Message msg) {

		switch (msg.what) {
		case 1:
			long size = (Long) msg.obj;
			total_size += size;
			tv_clear_file.setText(CommonUtil.getFileInfo(total_size));
			break;
		case 2:
			List<ClearBean> listViewData = (List<ClearBean>) msg.obj;
			System.out.println(listViewData);
			adapter.setDataToAdapter(listViewData);
			adapter.notifyDataSetChanged();
			 pb_clear.setVisibility(View.INVISIBLE);
			 lv_clear.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}

	private void asyncTaskLoadData() throws IOException, Exception {
		total_size = 0;
		 pb_clear.setVisibility(View.VISIBLE);
		 lv_clear.setVisibility(View.INVISIBLE);
		ClearManager.readClearDB(getResources().getAssets()
				.open("clearpath.db"));
		final List<ClearBean> listData = ClearManager
				.getPhoneSoftDetail(ClearActivity.this);
		new Thread() {
			public void run() {
				for (ClearBean cb : listData) {
					File file = new File(cb.getFilepath());
					long size = FileManager.getFileSize(file);
					cb.setSize(size);

					Message msg = handler.obtainMessage();// Message对象
					msg.what = 1;
					msg.obj = size;
					handler.sendMessage(msg);
				}
				Message msg = handler.obtainMessage();// Message对象
				msg.what = 2;
				msg.obj = listData;
				handler.sendMessage(msg);
			};
		}.start();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				startActivity(HomeActivity.class);
				finish();
				break;

			default:
				break;
			}

		}
	};

}
