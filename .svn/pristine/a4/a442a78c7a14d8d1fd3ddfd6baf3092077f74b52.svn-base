package com.example.test8_10_2.activity;

import java.util.List;

import com.example.test8_10_2.R;
import com.example.test8_10_2.adapter.TelShowAdapter;
import com.example.test8_10_2.base.BaseActivity;
import com.example.test8_10_2.bean.TableClass;
import com.example.test8_10_2.util.DBManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TelmgrShowActivity extends BaseActivity {

	private ListView lv_telshow;
	private TelShowAdapter adapter;
	private List<TableClass> list;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_telmgr_show);
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		int idx = intent.getIntExtra("idx", 1);
		initActionBar(title, R.drawable.btn_homeasup_default, -1,onClickListener);
		lv_telshow = (ListView) findViewById(R.id.lv_telshow);
		adapter = new TelShowAdapter(this);
		lv_telshow.setAdapter(adapter);
		lv_telshow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String number = adapter.getItem(position).getNumber()+"";
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));
				startActivity(intent);
			}
		});
		asyncTaskloadData(idx);
		System.out.println(list);
	}

	private void asyncTaskloadData(final int idx) {
		new Thread() {
			public void run() {
				list = DBManager.readTableClass("table" + idx);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						adapter.setDataToAdapter(list);
						adapter.notifyDataSetChanged();
					}
				});
			};
		}.start();

	}
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				startActivity(TelMgrActivity.class);
				finish();
			default:
				break;
			}

		}
	};

}
