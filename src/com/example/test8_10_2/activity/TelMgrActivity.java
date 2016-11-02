package com.example.test8_10_2.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.TelmgrGridAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.ClassInfo;
import com.example.test8_10_2.util.DBManager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class TelMgrActivity extends BaseActivity {

	private GridView gv_telmgr_view;
	private TelmgrGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel_mgr);

		initActionBar("通讯大全", R.drawable.btn_homeasup_default, -1, onClickListener);
		gv_telmgr_view = (GridView) findViewById(R.id.gv_telmgr_view);
		adapter = new TelmgrGridAdapter(this);
		gv_telmgr_view.setAdapter(adapter);
		gv_telmgr_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int idx = adapter.getDataList().get(position).getIdx();
				String name = adapter.getDataList().get(position).getName();
				Bundle budle = new Bundle();
				budle.putString("title", name);
				budle.putInt("idx", idx);
				startActivity(TelmgrShowActivity.class, budle);

			}

		});
		asyncTaskLoadData();

	}

	private void asyncTaskLoadData() {
		List<ClassInfo> list = new ArrayList<ClassInfo>();

		new Thread() {
			public void run() {
				try {
					DBManager.readUpdataDB(getResources().getAssets().open(
							"commonnum.db"));
					final List<ClassInfo> list = DBManager.readClassListTable();
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							adapter.setDataToAdapter(list);
							adapter.notifyDataSetChanged();

						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}

				List<ClassInfo> list = DBManager.readClassListTable();
				System.out.println(list);
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
